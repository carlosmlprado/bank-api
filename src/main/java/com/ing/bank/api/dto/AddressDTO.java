package com.ing.bank.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddressDTO implements Serializable {

    private Long id;
    private String street;
    private String number;
    private String city;
    private String postCode;
}
