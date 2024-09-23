package com.maltem.PaymentTransactionManagement.dao.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@AllArgsConstructor
public enum CustomerStatus {
    ACTIVE("active"),
    INACTIVE("inactive");

    @Getter
    private final String label;

    public static CustomerStatus enumFromString(String s) {
        if (s == null) return null;
        for (CustomerStatus customerStatus : CustomerStatus.values()) {
            if (customerStatus.label.equalsIgnoreCase(s)) return customerStatus;
        }
        return null;
    }
}
