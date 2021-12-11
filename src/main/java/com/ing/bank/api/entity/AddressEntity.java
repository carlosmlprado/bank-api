package com.ing.bank.api.entity;

import com.ing.bank.api.dto.AddressDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "address")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private CustomerEntity customer;

    public AddressEntity toEntity(AddressDTO addressDTO, CustomerEntity customer) {

        return AddressEntity.builder().
                street(addressDTO.getStreet()).
                number(addressDTO.getNumber()).
                city(addressDTO.getCity()).
                customer(customer).
                postCode(addressDTO.getPostCode()).build();
    }
}
