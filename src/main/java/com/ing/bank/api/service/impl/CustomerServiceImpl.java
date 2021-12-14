package com.ing.bank.api.service.impl;

import com.ing.bank.api.dto.address.AddressDTO;
import com.ing.bank.api.dto.customer.CustomerDTO;
import com.ing.bank.api.entity.CustomerEntity;
import com.ing.bank.api.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@AllArgsConstructor
public class CustomerServiceImpl {

    private CustomerRepository customerRepository;
    private AddressServiceImpl addressService;

    @Transactional
    public CustomerDTO createCustomer(CustomerDTO customerDTO) {
        var address = new AddressDTO();
        log.info("Creating new User. First checking if he/she already exists");
        var customerEntity = customerRepository.findCustomerByBsn(customerDTO.getBsn());
        log.debug("CustomerEntity: " + customerEntity);

        if (null == customerEntity) {
            customerEntity = new CustomerEntity();
            customerEntity = customerEntity.toEntity(customerDTO);
            customerRepository.save(customerEntity);

            log.info("Setting address to the customer");
        }


        log.info("Check address.");
        address = addressService.createAddress(customerDTO.getAddress(), customerEntity);

        customerDTO.setId(customerEntity.getId());
        customerDTO.setAddress(address);

        return customerDTO;
    }

}
