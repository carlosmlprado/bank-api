package com.ing.bank.api.dto;

import com.ing.bank.api.entity.BankAccountEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BankAccountResponseDTO implements Serializable {

    private String type;
    private Date openSince;
    private String status;
    private String iban;
    private String bank;

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
