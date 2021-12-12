package com.ing.bank.api.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Table(name = "rel_customer_transaction")
@AllArgsConstructor
@NoArgsConstructor
public class CustomerTransactionEntity {

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
