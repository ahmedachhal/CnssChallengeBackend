package com.maltem.PaymentTransactionManagement.batch.serviceBatch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlTypeValue;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Service
public class PaymentService {

    private final SimpleJdbcCall jdbcCall;

    @Autowired
    public PaymentService(DataSource dataSource) {
        this.jdbcCall = new SimpleJdbcCall(dataSource)
                .withProcedureName("PROCESS_BATCH_PAYMENTS")
                .declareParameters(
                        // Declare the output parameters
                        new SqlOutParameter("p_transaction_count", SqlTypeValue.TYPE_UNKNOWN),
                        new SqlOutParameter("p_total_amount", SqlTypeValue.TYPE_UNKNOWN),
                        new SqlOutParameter("p_error_code", SqlTypeValue.TYPE_UNKNOWN),
                        new SqlOutParameter("p_error_message", SqlTypeValue.TYPE_UNKNOWN)
                );
    }

    public BatchPaymentOutput processBatchPayments(String customerId) {
        Map<String, Object> inParams = new HashMap<>();
        inParams.put("p_customer_id", customerId);

        Map<String, Object> outParams = jdbcCall.execute(inParams);

        BatchPaymentOutput response = new BatchPaymentOutput();
        response.setTransactionCount((Integer) outParams.get("p_transaction_count"));
        response.setTotalAmount((BigDecimal) outParams.get("p_total_amount"));
        response.setErrorCode((String) outParams.get("p_error_code"));
        response.setErrorMessage((String) outParams.get("p_error_message"));

        return response;
    }
}
