package com.ing.bank.api.controller;

import com.ing.bank.api.dto.transaction.SpentAndReceivedMoneyResponseDTO;
import com.ing.bank.api.dto.transaction.TransactionDTO;
import com.ing.bank.api.service.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/transaction")
@AllArgsConstructor
public class TransactionController {

    private TransactionService transactionService;

    @PostMapping("/createTransaction")
    public ResponseEntity<String> createTransaction(@RequestBody TransactionDTO transactionDTO) {
        String resp = transactionService.createTransaction(transactionDTO);

        if (resp.equals("error"))
            return new ResponseEntity<String>("Error creating transaction", HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<String>(resp, HttpStatus.CREATED);
    }

    @GetMapping("/moneyReceivedAndSpentInTransactions/{customerId}")
    public ResponseEntity<SpentAndReceivedMoneyResponseDTO> moneyReceivedAndSpentInTransactions(@PathVariable Long customerId) {

        return null;
    }
}
