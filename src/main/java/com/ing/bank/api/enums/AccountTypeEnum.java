package com.ing.bank.api.enums;

public enum AccountTypeEnum {

    PRIVATE(1, "Private"),
    JOINT_ACCOUNT(2, "Joint-Account"),
    BUSINESS(3, "Business");

    private Integer key;
    private String description;

    private AccountTypeEnum(Integer key, String description) {
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
