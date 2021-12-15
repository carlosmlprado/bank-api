package com.ing.bank.api.service;

import com.ing.bank.api.entity.AddressEntity;
import com.ing.bank.api.repository.AddressRepository;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AddressServiceImplTest {

    @InjectMocks
    private AddressServiceImpl addressService;

    @Mock
    private static AddressRepository addressRepository;

    @Test
    public void when_passing_arguments_should_return_address() {

        AddressEntity address = toEntity();

        when(addressRepository.verifyIfAddressExistsByPostCodeAndNumberAndCustomerId(any(), any(), any())).thenReturn(address);

        assertEquals("Enschede", address.getCity());
        assertEquals("1", address.getNumber());
        assertEquals("7522DG", address.getPostCode());
        assertEquals("Somethingstraat", address.getStreet());
    }

    private AddressEntity toEntity() {

        return AddressEntity.builder().
                city("Enschede").
                number("1").
                postCode("7522DG").
                street("Somethingstraat").build();
    }

}