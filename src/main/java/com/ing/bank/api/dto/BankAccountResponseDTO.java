package com.ing.bank.api.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@Builder
public class BankAccountResponseDTO implements Serializable {

    private String type;
    private Date openSince;
    private String status;
    private String iban;
    private String bank;
}
