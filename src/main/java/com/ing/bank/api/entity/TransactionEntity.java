package com.ing.bank.api.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "transaction")
@Data
public class TransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
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
