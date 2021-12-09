package com.ing.bank.api.dto;

import lombok.Data;

import java.util.Date;

@Data
public class TransactionDTO {

    private Long id;
    private String transactionId;
    private String originBank;
    private String originAccount;
    private String originName;
    private String destinationBank;
    private String destinationAccount;
    private String destinationName;
    private Float amount;
    private Date transactionDate;
    private String status;

//    status: enum[valid, invalid]
}
