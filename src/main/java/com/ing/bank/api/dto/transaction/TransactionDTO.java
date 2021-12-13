package com.ing.bank.api.dto.transaction;

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
    private Date transactionDate;
    private String status;

}
