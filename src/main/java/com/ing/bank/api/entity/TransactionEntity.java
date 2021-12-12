package com.ing.bank.api.entity;

import com.ing.bank.api.dto.transaction.TransactionDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

@Entity
@Table(name = "transaction")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
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

    public TransactionEntity toEntity(TransactionDTO transactionDTO) {

        return TransactionEntity.builder().
                originBank(transactionDTO.getOriginBank()).
                originAccount(transactionDTO.getOriginAccount()).
                originName(transactionDTO.getOriginName()).
                destinationBank(transactionDTO.getDestinationBank()).
                destinationAccount(transactionDTO.getDestinationAccount()).
                destinationName(transactionDTO.getDestinationName()).
                amount(transactionDTO.getAmount()).
                transactionDate(Calendar.getInstance().getTime()).
                status(transactionDTO.getStatus()).build();

    }
}
