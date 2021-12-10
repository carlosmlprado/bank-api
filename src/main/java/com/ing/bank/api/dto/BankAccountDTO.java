package com.ing.bank.api.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class BankAccountDTO implements Serializable {

    private Long id;
    private String type;
    private List<CustomerDTO> customers;
    private Date openedDate;
    private String status;
}
