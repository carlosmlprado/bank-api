package com.ing.bank.api.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class AddressDTO implements Serializable {

    private Long id;
    private String street;
    private String number;
    private String city;
    private String postCode;
}
