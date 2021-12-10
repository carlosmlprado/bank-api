package com.ing.bank.api.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "rel_customer_transaction")
public class UserTransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_from_id")
    private CustomerEntity customerFromId;

    @ManyToOne
    @JoinColumn(name = "customer_to_id")
    private CustomerEntity customerToId;

    @ManyToOne
    @JoinColumn(name = "transaction_id")
    private TransactionEntity transactionId;

}
