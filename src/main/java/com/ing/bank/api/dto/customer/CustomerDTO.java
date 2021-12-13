package com.ing.bank.api.dto.customer;

import com.ing.bank.api.dto.address.AddressDTO;
import com.ing.bank.api.entity.CustomerEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO implements Serializable {

    private Long id;
    private String name;
    private Date customerSinceWhen;
    private AddressDTO address;
    private String bsn;

    public CustomerDTO toDTO(CustomerEntity customerEntity, AddressDTO addressDTO) {

        return CustomerDTO.builder().
                name(customerEntity.getName()).
                customerSinceWhen(customerEntity.getOpenSince()).
                address(addressDTO).build();

    }

}
