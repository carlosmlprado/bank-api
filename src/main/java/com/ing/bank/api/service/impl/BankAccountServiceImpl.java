package com.ing.bank.api.service.impl;

import com.ing.bank.api.dto.bankaccount.BankAccountDTO;
import com.ing.bank.api.dto.customer.CustomerDTO;
import com.ing.bank.api.entity.BankAccountEntity;
import com.ing.bank.api.entity.CustomerEntity;
import com.ing.bank.api.enums.BankAccountStatusEnum;
import com.ing.bank.api.enums.AccountTypeEnum;
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

    private final String PRIVATE = AccountTypeEnum.PRIVATE.getDescription();
    private final String JOINT_ACCOUNT = AccountTypeEnum.PRIVATE.getDescription();


    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public String createAccount(BankAccountDTO bankAccountDTO) {

        log.info("Begin of creating account. Verify if the request is valid.");
        var request = validRequestForCreatingAccount(bankAccountDTO);

        if (!request.equals("ok"))
            return request;

        log.info("Checking if customer exists. If not, it'll create a new one.");
        try {
            for (CustomerDTO c : bankAccountDTO.getOwners()) {
                log.info("It will create both customer and address.");
                c = customerService.createCustomer(c);

                log.info("Creating customerEntity to persist the bank account");
                var customerEntity = new CustomerEntity();

                log.info("Creating bankAccountEntity to persist.");
                var bankAccountEntity = new BankAccountEntity();
                bankAccountDTO.setIban(generateIban(bankAccountDTO.getBank().substring(0, 2)));
                bankAccountEntity = bankAccountEntity.toEntity(bankAccountDTO, customerEntity.toEntity(c));

                log.debug("bankAccountEntity: " + bankAccountEntity);
                bankAccountRepository.save(bankAccountEntity);
            }
        } catch (Exception e) {
            log.error("Error creating bank account for customers: " + e.getMessage());
            return "error";
        }
        return "Success creating account";
    }

    @Override
    @Transactional
    public HttpStatus deleteBankAccountsByCustomerId(Long customerId) {

        log.info("Verifying if customer has account..");
        var counter = bankAccountRepository.countCustomerAccounts(customerId);
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

    private String validRequestForCreatingAccount(BankAccountDTO bankAccountDTO) {
        log.info("Applying rules to deal with create account request");
        if (bankAccountDTO.getType().equals(PRIVATE) && bankAccountDTO.getOwners().size() != 1) {
            return "We can only create Private Account for 1 different customer at time";
        } else if (bankAccountDTO.getType().equals(JOINT_ACCOUNT) && bankAccountDTO.getOwners().size() != 2) {
            return "We can only create Joint Accounts for 2 different customers at time";
        } else {
            return "ok";
        }
    }
}
