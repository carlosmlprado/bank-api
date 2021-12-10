package com.ing.bank.api.controller;

import com.ing.bank.api.dto.CustomerDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/account")
@AllArgsConstructor
public class AccountController {


    @PostMapping("/createAccount")
    public ResponseEntity<?> createAccount(@RequestBody CustomerDTO customerDTO) {

        return null;
    }

    @GetMapping("/listCustomerAccount/{customerId}")
    public ResponseEntity<?> listCustomerAccount(@PathVariable Long customerId) {

        return null;
    }

    @DeleteMapping("/deleteAccounts/{customerId}")
    public ResponseEntity<?> deleteAccounts(@PathVariable Long customerId) {

        return null;
    }


}
