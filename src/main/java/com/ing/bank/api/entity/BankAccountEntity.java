package com.ing.bank.api.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "bank_account")
@Data
public class BankAccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "type")
    private String type;

    @ManyToMany
    @JoinColumn(name = "customer_id")
    private CustomerEntity customer;

    @Column(name = "open_date")
    private Date openedDate;
    @Column(name = "status")
    private String status;
    @Column(name = "iban")
    private String iban;
    @Column(name = "bank")
    private String bank;
}
