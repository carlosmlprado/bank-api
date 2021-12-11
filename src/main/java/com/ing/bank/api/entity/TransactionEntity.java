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
    @Column(name = "transaction_id")
    private String transactionId;
    @Column(name = "origin_bank")
    private String originBank;
    @Column(name = "origin_account")
    private String originAccount;
    @Column(name = "origin_name")
    private String originName;
    @Column(name = "destination_bank")
    private String destinationBank;
    @Column(name = "destination_account")
    private String destinationAccount;
    @Column(name = "destination_name")
    private String destinationName;
    @Column(name = "amount")
    private Float amount;
    @Column(name = "transaction_date")
    private Date transactionDate;
    @Column(name = "status")
    private String status;
}
