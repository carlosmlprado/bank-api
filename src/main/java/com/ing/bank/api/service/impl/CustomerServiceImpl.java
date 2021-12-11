package com.ing.bank.api.service.impl;

import com.ing.bank.api.dto.AddressDTO;
import com.ing.bank.api.dto.CustomerDTO;
import com.ing.bank.api.entity.CustomerEntity;
import com.ing.bank.api.repository.CustomerRepository;
import com.ing.bank.api.service.AddressService;
import com.ing.bank.api.service.CustomerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service("customerService")
@AllArgsConstructor
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;
    private AddressService addressService;

    @Override
    public CustomerDTO createCustomer(CustomerDTO customerDTO) {

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
        var address = createAddress(customerEntity, customerDTO.getAddress());

        customerDTO.setId(customerEntity.getId());
        customerDTO.setAddress(address);

        return customerDTO;
    }

    private AddressDTO createAddress(CustomerEntity customerEntity, AddressDTO addressDTO) {
        return addressService.createAddress(addressDTO, customerEntity);
    }


}
