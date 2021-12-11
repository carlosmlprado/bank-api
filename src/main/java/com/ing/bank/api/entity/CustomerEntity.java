package com.ing.bank.api.entity;

import com.ing.bank.api.dto.CustomerDTO;
import lombok.*;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

@Entity
@Table(name = "customer")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "bsn", unique = true)
    private String bsn;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "open_since")
    private Date openSince;

    public CustomerEntity toEntity(CustomerDTO customer) {

        return CustomerEntity.builder().
                id(customer.getId()).
                name(customer.getName()).
                openSince(Calendar.getInstance().getTime()).
                bsn(customer.getBsn()).build();
    }
}
