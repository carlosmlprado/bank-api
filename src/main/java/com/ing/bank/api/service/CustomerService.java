package com.ing.bank.api.service;

import com.ing.bank.api.dto.CustomerDTO;

public interface CustomerService {

    /**
     * @param customer
     * @return customerId
     */

    CustomerDTO createCustomer(CustomerDTO customer);
}
