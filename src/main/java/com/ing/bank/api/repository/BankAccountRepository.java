package com.ing.bank.api.repository;

import com.ing.bank.api.entity.BankAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccountEntity, Long> {

    @Query(value = "SELECT * from bank_account WHERE customer_id = :customerId", nativeQuery = true)
    List<BankAccountEntity> listCustomerAccounts(@Param("customerId") Long customerId);

    @Modifying
    @Query(value = "delete from bank_account where customer_id = :customerId", nativeQuery = true)
    void deleteBankAccountsByCustomerId(@Param("customerId")Long customerId);

    @Query(value = "SELECT count(id) from bank_account WHERE customer_id = :customerId", nativeQuery = true)
    Long countCustomerAccounts(@Param("customerId") Long customerId);

    @Query(value = "SELECT customer_id from bank_account where iban = :customerIban AND status = 'Active'", nativeQuery = true)
    Long getUserIdFromBankAccountByIban(@Param("customerIban") String customerIban);

    @Query(value = "SELECT customer_id from bank_account where iban = :customerIban AND status = 'Active' AND type = :type AND bank = :bank", nativeQuery = true)
    Long getUserIdFromBankAccountByIbanAndTypeAndBank(@Param("customerIban") String customerIban, @Param("type") String type, @Param("bank") String bank);
}
