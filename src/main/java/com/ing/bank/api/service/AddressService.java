package com.ing.bank.api.service;

import com.ing.bank.api.dto.address.AddressDTO;
import com.ing.bank.api.entity.CustomerEntity;

public interface AddressService {

    AddressDTO createAddress(AddressDTO addressDTO, CustomerEntity customerEntity);
}
