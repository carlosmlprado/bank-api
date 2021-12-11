package com.ing.bank.api.entity;

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

    public BankAccountEntity toEntity(String type, CustomerEntity customer, String status, String iban) {

        return BankAccountEntity.builder().
                type(type).
                customer(customer).
                openedDate(Calendar.getInstance().getTime()).
                status(status).
                iban(iban).
                bank(BanksEnum.ING.getDescription()).build();
    }

}
