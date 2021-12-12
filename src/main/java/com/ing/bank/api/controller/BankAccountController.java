package com.ing.bank.api.controller;

import com.ing.bank.api.converter.Converter;
import com.ing.bank.api.dto.BankAccountDTO;
import com.ing.bank.api.dto.BankAccountResponseDTO;
import com.ing.bank.api.repository.BankAccountRepository;
import com.ing.bank.api.service.BankAccountService;
import lombok.AllArgsConstructor;
import lombok.NonNull;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/account")
@AllArgsConstructor
public class BankAccountController {

    private BankAccountService bankAccountService;
    private BankAccountRepository bankAccountRepository;
    private Converter converter;

    @PostMapping("/createAccount")
    public ResponseEntity<Void> createAccount(@RequestBody BankAccountDTO account) {
        Boolean resp = bankAccountService.createAccount(account);
        if (resp)
            return ResponseEntity.status(HttpStatus.CREATED).build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @GetMapping("/listCustomerAccount/{customerId}")
    public ResponseEntity<List<BankAccountResponseDTO>> listCustomerAccounts(@NonNull @PathVariable Long customerId) {
        List<BankAccountResponseDTO> list = bankAccountRepository.listCustomerAccounts(customerId).stream().map(converter::toDTO).collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

    @DeleteMapping("/deleteAccounts/{customerId}")
    public ResponseEntity<?> deleteAccounts(@NonNull @PathVariable Long customerId) {

        return null;
    }


}
