package com.ing.bank.api.enums;

public enum AccountStatusEnum {

    ACTIVE(1, "Active"),
    BLOCKED(2, "Blocked"),
    CLOSED(3, "Closed");

    private Integer key;
    private String description;

    private AccountStatusEnum(Integer key, String description) {
        this.key = key;
        this.description = description;
    }

    public Integer getKey(Integer key) {
        return key;
    }

    public String getDescription(String description) {
        return description;
    }

}
