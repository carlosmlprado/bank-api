package com.ing.bank.api.service.impl;

import com.ing.bank.api.dto.transaction.SentAndReceivedMoneyResponseDTO;
import com.ing.bank.api.dto.transaction.TransactionDTO;
import com.ing.bank.api.entity.CustomerEntity;
import com.ing.bank.api.entity.CustomerTransactionEntity;
import com.ing.bank.api.entity.TransactionEntity;
import com.ing.bank.api.enums.BanksEnum;
import com.ing.bank.api.enums.TransactionStatusEnum;
import com.ing.bank.api.repository.BankAccountRepository;
import com.ing.bank.api.repository.CustomerRepository;
import com.ing.bank.api.repository.TransactionRepository;
import com.ing.bank.api.repository.CustomerTransactionRepository;
import com.ing.bank.api.service.TransactionService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service("transactionService")
@AllArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private TransactionRepository transactionRepository;
    private CustomerTransactionRepository customerTransactionRepository;
    private BankAccountRepository bankAccountRepository;
    private CustomerRepository customerRepository;

    private final String INVALID = TransactionStatusEnum.INVALID.getDescription();
    private final String VALID = TransactionStatusEnum.VALID.getDescription();
    private final String ING = BanksEnum.ING.getDescription();

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public String createTransaction(TransactionDTO transactionDTO) {
        String resp = "Transaction created with status ";
        String originBank = transactionDTO.getOriginBank();
        String destinationBank = transactionDTO.getDestinationBank();


        log.info("Check if one of the customers has account in ING to be a VALID transaction.");
        String status = checkIfOneOfTheCustomersAreFromIng(originBank, destinationBank);

        try {
            if (status.equals(INVALID)) {
                log.info("Creating new INVALID Transaction {} ", transactionDTO);
                TransactionEntity transactionEntity = createTransactionEntityBasedOnStatus(transactionDTO, TransactionStatusEnum.INVALID.getDescription());
                transactionRepository.save(transactionEntity);
                return resp += status;
            }

            var customerIdFrom = originBank.equals(ING) ?
                    getUserIdFromBankAccountByIban(transactionDTO.getOriginAccount()) : 0;

            var customerIdTo = destinationBank.equals(ING) ?
                    getUserIdFromBankAccountByIban(transactionDTO.getDestinationAccount()) : 0;

            TransactionEntity transactionEntity = createTransactionEntityBasedOnStatus(transactionDTO, TransactionStatusEnum.VALID.getDescription());
            transactionRepository.save(transactionEntity);

            log.info("Transaction created... Now relating the users with the transaction.");
            createNewRelationBetwenCustomersAndTransaction(customerIdFrom, customerIdTo, transactionEntity);

            return resp += status;

        } catch (Exception e) {
            log.error("Error trying to create new Transaction: " + e.getMessage());
            return "error";
        }
    }

    private Long getUserIdFromBankAccountByIban(String customerIban) {
        Long customerId = bankAccountRepository.getUserIdFromBankAccountByIban(customerIban.trim());
        if (null == customerId) {
            return 0L;
        }
        return customerId;
    }

    private TransactionEntity createTransactionEntityBasedOnStatus(TransactionDTO transactionDTO, String status) {
        log.info("Creating new {} Transaction", status);
        var transactionEntity = new TransactionEntity();
        transactionEntity = transactionEntity.toEntity(transactionDTO);
        transactionEntity.setStatus(status);

        return transactionEntity;
    }

    private String checkIfOneOfTheCustomersAreFromIng(String originBank, String destinationBank) {
        if (!originBank.equals(ING) &&
                !destinationBank.equals(ING)) {
            return INVALID;
        } else {
            return VALID;
        }
    }

    private void createNewRelationBetwenCustomersAndTransaction(Long customerIdFrom, Long customerIdTo, TransactionEntity transaction) {
        var customerTransactionEntity = new CustomerTransactionEntity();
        var customerFromEntity = customerRepository.findById(customerIdFrom).orElse(null);
        var customerToEntity = customerRepository.findById(customerIdTo).orElse(null);
        customerTransactionEntity = customerTransactionEntity.toEntity(customerFromEntity, customerToEntity, transaction);

        customerTransactionRepository.save(customerTransactionEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public SentAndReceivedMoneyResponseDTO getMoneyReceivedAndSpentInTransactionsByCustomerId(Long customerId) {
        SentAndReceivedMoneyResponseDTO spentAndReceivedMoneyResponseDTO = new SentAndReceivedMoneyResponseDTO(0F, 0F, 0F, "");

        log.info("Verifying if customer exists");
        CustomerEntity customerEntity = customerRepository.findById(customerId).stream().findFirst().orElse(null);
        if (null == customerEntity) {
            log.info("Customer doesn't exist");
            return spentAndReceivedMoneyResponseDTO;
        }
        log.info("Customer exists {}", customerEntity);
        spentAndReceivedMoneyResponseDTO.setCustomerName(customerEntity.getName());

        log.info("Getting all transactionsId that customer sent and received money");
        var transactionIdsTo = customerTransactionRepository.getTransactionIdsToCustomerId(customerId);
        var transactionIdsFrom = customerTransactionRepository.getTransactionIdsFromCustomerId(customerId);

        List<Float> receivedAmounts = new ArrayList<>();
        List<Float> sentAmounts = new ArrayList<>();
        Double totalReceivedAmount = 0.0;
        Double totalSentAmount = 0.0;

        if (!transactionIdsFrom.isEmpty()) {
            log.info("Getting amounts that customer already sent of money");
            sentAmounts = transactionRepository.getAmountByTransactionId(transactionIdsFrom, VALID);
            totalSentAmount = sentAmounts.stream().mapToDouble(f -> f.doubleValue()).sum();
            spentAndReceivedMoneyResponseDTO.setMoneySent(Float.valueOf(totalSentAmount.floatValue()));
        }

        if (!transactionIdsTo.isEmpty()) {
            log.info("Getting amounts that customer already received of money");
            receivedAmounts = transactionRepository.getAmountByTransactionId(transactionIdsTo, VALID);
            totalReceivedAmount = receivedAmounts.stream().mapToDouble(f -> f.doubleValue()).sum();
            spentAndReceivedMoneyResponseDTO.setMoneyReceived(Float.valueOf(totalReceivedAmount.floatValue()));
        }

        spentAndReceivedMoneyResponseDTO.setTotal(totalReceivedAmount.floatValue() - totalSentAmount.floatValue());

        return spentAndReceivedMoneyResponseDTO;
    }
}
