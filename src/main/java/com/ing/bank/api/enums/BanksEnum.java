package com.ing.bank.api.enums;

public enum BanksEnum {

    PRIVATE(1, "ING"),
    JOINT_ACCOUNT(2, "ABN AMRO"),
    BUSINESS(3, "RABOBANK");

    private Integer key;
    private String description;

    private BanksEnum(Integer key, String description) {
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
