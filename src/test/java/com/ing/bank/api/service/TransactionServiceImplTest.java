package com.ing.bank.api.service;

import com.ing.bank.api.dto.transaction.TransactionDTO;
import com.ing.bank.api.entity.TransactionEntity;
import com.ing.bank.api.repository.TransactionRepository;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TransactionServiceImplTest {

    @Mock
    private static TransactionRepository transactionRepository;

    @Test
    public void when_passing_at_least_one_ing_bank_account_should_create_valid_transaction() {

        when(transactionRepository.save(any())).thenReturn(toEntityValid());
        TransactionDTO transactionDTO = toDtoValid();
        TransactionEntity transactionEntity = transactionRepository.save(toEntityValid());

        assertEquals(transactionEntity.getDestinationBank(), transactionDTO.getDestinationBank());
        assertEquals(transactionEntity.getOriginAccount(), transactionDTO.getOriginAccount());
        assertEquals(transactionEntity.getOriginBank(), transactionDTO.getOriginBank());
        assertEquals(transactionEntity.getStatus(), transactionDTO.getStatus());

    }

    @Test
    public void when_passing_no_ing_bank_account_should_not_create_valid_transaction() {

        when(transactionRepository.save(any())).thenReturn(toEntityInalid());
        TransactionDTO transactionDTO = toDtoInValid();
        TransactionEntity transactionEntity = transactionRepository.save(toEntityInalid());

        assertEquals(transactionEntity.getDestinationBank(), transactionDTO.getDestinationBank());
        assertEquals(transactionEntity.getOriginAccount(), transactionDTO.getOriginAccount());
        assertEquals(transactionEntity.getOriginBank(), transactionDTO.getOriginBank());
        assertEquals(transactionEntity.getStatus(), transactionDTO.getStatus());
    }

    private TransactionDTO toDtoValid() {
        return TransactionDTO.builder().
                amount(19F).
                destinationBank("ING").
                destinationAccount("1234").
                destinationName("Joaquim").
                originBank("ABN-AMRO").
                originAccount("123456").
                originName("Malaquias").
                status("Valid").build();
    }

    private TransactionDTO toDtoInValid() {
        return TransactionDTO.builder().
                amount(19F).
                destinationBank("RABOBANK").
                destinationAccount("1234").
                destinationName("Joaquim").
                originBank("ABN-AMRO").
                originAccount("123456").
                originName("Malaquias").status("Invalid").build();
    }

    private TransactionEntity toEntityValid() {
        return TransactionEntity.builder().
                amount(19F).
                destinationBank("ING").
                destinationAccount("1234").
                destinationName("Joaquim").
                originBank("ABN-AMRO").
                originAccount("123456").
                originName("Malaquias").
                status("Valid").build();
    }

    private TransactionEntity toEntityInalid() {
        return TransactionEntity.builder().
                amount(19F).
                destinationBank("RABOBANK").
                destinationAccount("1234").
                destinationName("Joaquim").
                originBank("ABN-AMRO").
                originAccount("123456").
                originName("Malaquias").
                status("Invalid").build();
    }
}