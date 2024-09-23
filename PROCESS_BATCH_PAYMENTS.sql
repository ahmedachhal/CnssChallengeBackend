CREATE OR REPLACE PROCEDURE PROCESS_BATCH_PAYMENTS (
    p_customer_id IN VARCHAR2,
    p_transaction_count OUT INTEGER,
    p_total_amount OUT DECIMAL,
    p_error_code OUT VARCHAR2,
    p_error_message OUT VARCHAR2
) AS
    v_amount DECIMAL(10, 2);
    v_processed_count INTEGER := 0;
    v_total_amount DECIMAL(10, 2) := 0;
    
BEGIN
    -- Initialize output parameters
    p_transaction_count := 0;
    p_total_amount := 0;
    p_error_code := NULL;
    p_error_message := NULL;

    
    FOR rec IN (SELECT a.id as id , a.amount as amount
                FROM transaction a, account b, customer c
                WHERE 
                b.customer_id = c.id
                AND c.id = p_customer_id AND a.transactionStatus = 'PENDING') LOOP
        BEGIN
            
            v_amount := rec.amount;
            
            if v_amount <= 0 
                UPDATE transaction
                SET status = 'FAILED ',
                        transactionDate = SYSDATE
                WHERE id = rec.id;
            ELSE 
                UPDATE transaction
                SET status = 'COMPLETED ',
                        transactionDate = SYSDATE
                WHERE id = rec.id;

            -- Update totals
            v_processed_count := v_processed_count + 1;
            v_total_amount := v_total_amount + v_amount;

        EXCEPTION
            WHEN OTHERS THEN
                -- Handle error, set error code and message
                p_error_code := SQLCODE;
                p_error_message := SQLERRM;
                RAISE;  -- Optionally re-raise the exception if needed
        END;
    END LOOP;

    -- Set output parameters
    p_transaction_count := v_processed_count;
    p_total_amount := v_total_amount;

    -- Commit the transaction if all processed successfully
    COMMIT;

EXCEPTION
    WHEN OTHERS THEN
        -- Handle unexpected errors
        p_error_code := SQLCODE;
        p_error_message := SQLERRM;
        ROLLBACK;  -- Rollback in case of failure
END PROCESS_BATCH_PAYMENTS;

