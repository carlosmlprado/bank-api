package com.ing.bank.api.repository;

import com.ing.bank.api.entity.BankAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccountEntity, Long> {

    @Query(value = "SELECT * from bank_account WHERE customer_id = :customerId", nativeQuery = true)
    List<BankAccountEntity> listCustomerAccounts(@Param("customerId") Long customerId);
}
