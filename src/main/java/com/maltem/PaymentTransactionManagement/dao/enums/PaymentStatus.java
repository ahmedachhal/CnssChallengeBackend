package com.maltem.PaymentTransactionManagement.dao.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

public enum PaymentStatus {
    ACTIVE("active"),
    INACTIVE("inactive");

    @Getter
    private final String label;

    // Custom constructor for the enum
    PaymentStatus(String label) {
        this.label = label;
    }

    // Static method to get enum from a string
    public static PaymentStatus enumFromString(String s) {
        if (s == null) return null;
        for (PaymentStatus paymentStatus : PaymentStatus.values()) {
            if (paymentStatus.label.equalsIgnoreCase(s)) return paymentStatus;
        }
        return null;
    }
}
