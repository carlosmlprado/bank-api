package com.ing.bank.api.service.impl;

import com.ing.bank.api.dto.bankaccount.BankAccountDTO;
import com.ing.bank.api.dto.customer.CustomerDTO;
import com.ing.bank.api.entity.BankAccountEntity;
import com.ing.bank.api.entity.CustomerEntity;
import com.ing.bank.api.enums.AccountStatusEnum;
import com.ing.bank.api.repository.BankAccountRepository;
import com.ing.bank.api.service.BankAccountService;
import com.ing.bank.api.service.CustomerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

@Slf4j
@Service("BankAccountService")
@AllArgsConstructor
public class BankAccountServiceImpl implements BankAccountService {

    private BankAccountRepository bankAccountRepository;
    private CustomerService customerService;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Boolean createAccount(BankAccountDTO account) {

        log.info("Begin of createAccount. First checking if customer exists. If not, it'll create a new one.");
        try {
            for (CustomerDTO c : account.getOwners()) {
                log.info("It will create both customer and address.");
                c = customerService.createCustomer(c);

                log.info("Creating customerEntity to persist the bank account");
                var customerEntity = new CustomerEntity();

                log.info("Creating bankAccountEntity to persist.");
                var bankAccountEntity = new BankAccountEntity();
                bankAccountEntity = bankAccountEntity.toEntity(account.getType(), customerEntity.toEntity(c), AccountStatusEnum.ACTIVE.getDescription(), generateIban("IN"));

                log.debug("bankAccountEntity: " + bankAccountEntity);
                bankAccountRepository.save(bankAccountEntity);

            }
        } catch (Exception e) {
            log.error("Error creating bank account for customers: " + e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    @Transactional
    public HttpStatus deleteBankAccountsByCustomerId(Long customerId) {

        log.info("Verifying iof customer has account..");
        Long counter = bankAccountRepository.countCustomerAccounts(customerId);
        log.info("Customer has {} accounts", counter);
        if (counter > 0) {
            log.info("Deleting accounts from customer: {}", customerId);
            bankAccountRepository.deleteBankAccountsByCustomerId(customerId);
            return HttpStatus.NO_CONTENT;
        } else {
            return HttpStatus.NOT_FOUND;
        }
    }

    private String generateIban(String prefix) {
        StringBuilder iban = new StringBuilder();
        Random rand = new Random();

        for (int i = 0; i < 14; i++) {
            int n = rand.nextInt(10) + 0;
            prefix += Integer.toString(n);
        }
        for (int i = 0; i < 16; i++) {
            iban.append(prefix.charAt(i));
        }
        return iban.toString();
    }

}
