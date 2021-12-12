package com.ing.bank.api.dto.bankaccount;

import com.ing.bank.api.dto.customer.CustomerDTO;
import com.ing.bank.api.entity.BankAccountEntity;
import com.ing.bank.api.enums.BanksEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BankAccountDTO implements Serializable {

    private String type;

    private Date openSince;

    private String status;
    private String iban;
    private String bank;
    private List<CustomerDTO> owners;

    public BankAccountDTO toDtoWithCustomers(BankAccountEntity bankAccountEntity, List<CustomerDTO> customers) {
        return BankAccountDTO.builder().
                type(bankAccountEntity.getType()).
                openSince(bankAccountEntity.getOpenedDate()).
                iban(bankAccountEntity.getIban()).
                status(bankAccountEntity.getStatus()).
                bank(BanksEnum.ING.getDescription()).
                owners(customers).
                build();
    }
}
