package com.ing.bank.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
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
@NoArgsConstructor
@AllArgsConstructor
public class BankAccountDTO implements Serializable {

    private Long id;
    private String type;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
    private Date openSince;

    private String status;
    private String iban;
    private String bank;
    private List<CustomerDTO> owners;

    public BankAccountDTO toDTO(BankAccountEntity bankAccountEntity, List<CustomerDTO> customers) {
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
