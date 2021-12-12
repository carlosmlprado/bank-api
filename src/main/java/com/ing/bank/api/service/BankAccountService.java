package com.ing.bank.api.service;

import com.ing.bank.api.dto.BankAccountDTO;

public interface BankAccountService {

    /**
     *
     * @param account
     */
    Boolean createAccount(BankAccountDTO account);

}
