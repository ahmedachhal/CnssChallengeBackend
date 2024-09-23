package com.maltem.PaymentTransactionManagement.dao.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
@AllArgsConstructor
public enum AccountStatus {
    ACTIVE("active"),
    CLOSED("closed");

    @Getter
    private final String label;



    // Static method to get enum from a string
    public static AccountStatus enumFromString(String s) {
        if (s == null) return null;
        for (AccountStatus accountStatus : AccountStatus.values()) {
            if (accountStatus.label.equalsIgnoreCase(s)) return accountStatus;
        }
        return null;
    }
}
