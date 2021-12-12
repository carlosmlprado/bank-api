package com.ing.bank.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
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
