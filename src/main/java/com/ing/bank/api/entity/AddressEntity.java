package com.ing.bank.api.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "address")
@Data
public class AddressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "street")
    private String street;
    @Column(name = "number")
    private String number;
    @Column(name = "city")
    private String city;
    @Column(name = "post_code")
    private String postCode;

    @ManyToMany
    @JoinColumn(name = "customer_id")
    private CustomerEntity customer;
}
