package com.ing.bank.api.dto;

import lombok.Data;

import java.util.Date;

@Data
public class CustomerDTO {

    private Long id;
    private String name;
    private Date customerSinceWhen;

}
