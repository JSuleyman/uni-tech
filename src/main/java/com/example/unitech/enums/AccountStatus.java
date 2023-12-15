package com.example.unitech.enums;

public enum AccountStatus {
    ACTIVE("AKTİV"),
    DEACTIVE("DEAKTİV");

    private final String value;

    AccountStatus(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static AccountStatus fromValue(String value) {
        if (value == null) {
            return null;
        }
        for (AccountStatus status : values()) {
            if (status.name().equalsIgnoreCase(value)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid Status value: " + value);
    }
}
