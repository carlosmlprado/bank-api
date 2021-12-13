package com.ing.bank.api.enums;

public enum BankAccountStatusEnum {

    ACTIVE(1, "Active"),
    BLOCKED(2, "Blocked"),
    CLOSED(3, "Closed");

    private Integer key;
    private String description;

    private BankAccountStatusEnum(Integer key, String description) {
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
