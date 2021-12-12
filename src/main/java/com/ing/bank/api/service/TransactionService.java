package com.ing.bank.api.service;

import com.ing.bank.api.dto.transaction.TransactionDTO;

public interface TransactionService {

    /**
     * @param transactionDTO
     */
    String createTransaction(TransactionDTO transactionDTO);

}
