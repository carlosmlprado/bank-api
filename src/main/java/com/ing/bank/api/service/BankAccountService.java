package com.ing.bank.api.service;

import com.ing.bank.api.dto.bankaccount.BankAccountDTO;
import org.springframework.http.HttpStatus;

public interface BankAccountService {

    /**
     *
     * @param account
     */
    Boolean createAccount(BankAccountDTO account);

    /**
     *
     * @param customerId
     */
    HttpStatus deleteBankAccountsByCustomerId(Long customerId);

}
