package com.ing.bank.api.service;

import com.ing.bank.api.repository.BankAccountRepository;
import org.mockito.junit.MockitoJUnitRunner;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import static org.junit.Assert.assertEquals;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BankAccountServiceImplTest {

    @InjectMocks
    private BankAccountServiceImpl bankAccountService;

    @Mock
    private static BankAccountRepository bankAccountRepository;

    @Test
    public void when_passing_valid_customer_id_should_delete_and_return_no_content() {

        when(bankAccountRepository.countCustomerAccounts(90L)).thenReturn(1L);

        assertEquals(bankAccountService.deleteBankAccountsByCustomerId(90L), HttpStatus.NO_CONTENT);
    }

    @Test
    public void when_passing_invalid_customer_id_should_not_delete_and_return_not_found() {

        when(bankAccountRepository.countCustomerAccounts(90L)).thenReturn(0L);

        assertEquals(bankAccountService.deleteBankAccountsByCustomerId(90L), HttpStatus.NOT_FOUND);
    }

}