package com.ing.bank.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@Builder
public class TransactionDTO implements Serializable {

    private Long id;
    private String transactionId;
    private String originBank;
    private String originAccount;
    private String originName;
    private String destinationBank;
    private String destinationAccount;
    private String destinationName;
    private Float amount;
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
    private Date transactionDate;
    private String status;

}
