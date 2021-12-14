package com.ing.bank.api.entity;

import com.ing.bank.api.dto.bankaccount.BankAccountDTO;
import com.ing.bank.api.enums.BankAccountStatusEnum;
import com.ing.bank.api.enums.BanksEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

@Entity
@Table(name = "bank_account")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BankAccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "type")
    private String type;
    @Column(name = "open_date")
    private Date openedDate;
    @Column(name = "status")
    private String status;
    @Column(name = "iban")
    private String iban;
    @Column(name = "bank")
    private String bank;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private CustomerEntity customer;

    public BankAccountEntity toEntity(BankAccountDTO bankAccountDTO, CustomerEntity customer) {

        return BankAccountEntity.builder().
                type(bankAccountDTO.getType()).
                customer(customer).
                openedDate(Calendar.getInstance().getTime()).
                status(BankAccountStatusEnum.ACTIVE.getDescription()).
                iban(bankAccountDTO.getIban()).
                bank(bankAccountDTO.getBank()).build();
    }

}
