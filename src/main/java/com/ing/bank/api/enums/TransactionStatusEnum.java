package com.ing.bank.api.enums;

public enum TransactionStatusEnum {

    VALID(1, "Valid"),
    INVALID(2, "Invalid");

    private Integer key;
    private String description;

    private TransactionStatusEnum(Integer key, String description) {
        this.key = key;
        this.description = description;
    }

    public Integer getKey() {
        return key;
    }

    public String getDescription() {
        return description;
    }
}
