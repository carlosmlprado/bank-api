package com.ing.bank.api.controller;

import com.ing.bank.api.dto.transaction.SentAndReceivedMoneyResponseDTO;
import com.ing.bank.api.dto.transaction.TransactionDTO;
import com.ing.bank.api.service.impl.TransactionServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/transaction")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class TransactionController {

    private TransactionServiceImpl transactionService;

    @PostMapping("/createTransaction")
    public ResponseEntity<String> createTransaction(@RequestBody TransactionDTO transactionDTO) {
        String resp = transactionService.createTransaction(transactionDTO);

        if (resp.equals("error"))
            return new ResponseEntity<String>("Error creating transaction", HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<String>(resp, HttpStatus.CREATED);
    }

    @GetMapping("/moneyReceivedAndSentInTransactions/{customerId}")
    public ResponseEntity<SentAndReceivedMoneyResponseDTO> moneyReceivedAndSpentInTransactions(@PathVariable Long customerId) {
        SentAndReceivedMoneyResponseDTO receivedMoneyResponseDTO = transactionService.getMoneyReceivedAndSpentInTransactionsByCustomerId(customerId);
        if(null == receivedMoneyResponseDTO || receivedMoneyResponseDTO.getCustomerName().isEmpty())
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(receivedMoneyResponseDTO);
    }
}
