package com.ing.bank.api.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class CustomerDTO implements Serializable {

    private Long id;
    private String name;
    private Date customerSinceWhen;
    private AddressDTO address;

}
