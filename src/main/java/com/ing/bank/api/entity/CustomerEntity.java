package com.ing.bank.api.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "customer")
@Data
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "open_since")
    private Date openSince;

    @Column(name = "status")
    private String status;

    @Column(name = "bsn", unique = true)
    private String bsn;



}
