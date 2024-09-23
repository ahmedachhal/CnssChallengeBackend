package com.maltem.PaymentTransactionManagement.batch.serviceBatch;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BatchPaymentOutput {

    private Integer transactionCount;
    private BigDecimal totalAmount;
    private String errorCode;
    private String errorMessage;

}
