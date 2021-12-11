package com.ing.bank.api.controller;

import com.ing.bank.api.dto.BankAccountDTO;
import com.ing.bank.api.service.AccountService;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/account")
@AllArgsConstructor
public class AccountController {

    private AccountService accountService;

    @PostMapping("/createAccount")
    public ResponseEntity<Void> createAccount(@RequestBody BankAccountDTO account) {
        Boolean resp = accountService.createAccount(account);
        if (resp)
            return ResponseEntity.status(HttpStatus.CREATED).build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

    }

    @GetMapping("/listCustomerAccount/{customerId}")
    public ResponseEntity<?> listCustomerAccount(@NonNull @PathVariable Long customerId) {

        return null;
    }

    @DeleteMapping("/deleteAccounts/{customerId}")
    public ResponseEntity<?> deleteAccounts(@NonNull @PathVariable Long customerId) {

        return null;
    }


}
