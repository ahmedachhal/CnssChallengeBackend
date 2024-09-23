package com.maltem.PaymentTransactionManagement.dao.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

public enum TransactionsStatus {
    PENDING("pending"),
    COMPLETED("completed"),
    FAILED("failed"),
    CANCELLED("cancelled");

    @Getter
    private final String label;

    // Custom constructor for the enum
    TransactionsStatus(String label) {
        this.label = label;
    }

    // Static method to get enum from a string
    public static TransactionsStatus enumFromString(String s) {
        if (s == null) return null;
        for (TransactionsStatus transactionStatus : TransactionsStatus.values()) {
            if (transactionStatus.label.equalsIgnoreCase(s)) return transactionStatus;
        }
        return null;
    }
}
