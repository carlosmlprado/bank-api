package com.ing.bank.api.controller;

import com.ing.bank.api.dto.SpentAndReceivedMoneyResponseDTO;
import com.ing.bank.api.dto.TransactionDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/transaction")
@AllArgsConstructor
public class TransactionController {

    @PostMapping("/createTransaction")
    public ResponseEntity<Void> createTransaction(@RequestBody TransactionDTO transactionDTO){

        return null;
    }

    @GetMapping("/moneyReceivedAndSpentInTransactions/{customerId}")
    public ResponseEntity<SpentAndReceivedMoneyResponseDTO> moneyReceivedAndSpentInTransactions(@PathVariable Long customerId){

        return null;
    }
}
