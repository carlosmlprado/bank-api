package com.ing.bank.api.service.impl;

import com.ing.bank.api.dto.AddressDTO;
import com.ing.bank.api.entity.AddressEntity;
import com.ing.bank.api.entity.CustomerEntity;
import com.ing.bank.api.repository.AddressRepository;
import com.ing.bank.api.service.AddressService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service("addresService")
@AllArgsConstructor
@NoArgsConstructor
public class AddressServiceImpl implements AddressService {

    @Autowired
    AddressRepository addressRepository;

    @Override
    @Transactional
    public AddressDTO createAddress(AddressDTO addressDTO, CustomerEntity customerEntity) {
        log.info("Searching address. If it's already persisted, returns it, otherwise, create a new one.");

        var address = addressRepository.verifyIfAddressExistsByPostCodeAndNumber(addressDTO.getPostCode(), addressDTO.getNumber()   );

        if (null == address) {
            log.info("Creating a new address");

            address = new AddressEntity();
            address = address.toEntity(addressDTO, customerEntity);
            log.debug("AddressEntity: " + address);
            addressRepository.save(address);
        }
        addressDTO.setId(address.getId());
        return addressDTO;
    }
}
