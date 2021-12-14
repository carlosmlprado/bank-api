package com.ing.bank.api.enums;

public enum BanksEnum {

    ING(1, "ING"),
    ABN_AMRO(2, "ABN-AMRO"),
    RABOBANK(3, "RABOBANK");

    private Integer key;
    private String description;

    private BanksEnum(Integer key, String description) {
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
