package com.maltem.PaymentTransactionManagement.service.Exception;

public class AccountNotFoundException extends  RuntimeException {

    public AccountNotFoundException(String message) {
        super(message);
    }

    public AccountNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
