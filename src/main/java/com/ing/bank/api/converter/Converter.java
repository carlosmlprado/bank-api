package com.ing.bank.api.converter;

import com.ing.bank.api.dto.BankAccountResponseDTO;
import com.ing.bank.api.entity.BankAccountEntity;
import org.springframework.stereotype.Component;

@Component
public class Converter {

    public BankAccountResponseDTO toDTO(BankAccountEntity bankAccountEntity){
        return BankAccountResponseDTO.builder().
                type(bankAccountEntity.getType()).
                openSince(bankAccountEntity.getOpenedDate()).
                iban(bankAccountEntity.getIban()).
                status(bankAccountEntity.getStatus()).
                bank(bankAccountEntity.getBank()).
                build();
    }
}
