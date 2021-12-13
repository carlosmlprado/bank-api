package com.ing.bank.api.repository;

import com.ing.bank.api.entity.CustomerTransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerTransactionRepository extends JpaRepository<CustomerTransactionEntity, Long> {

    @Query(value = "SELECT transaction_id from rel_customer_transaction where customer_from_id = :customerFromId", nativeQuery = true)
    List<Long> getTransactionIdsFromCustomerFromId(@Param("customerFromId") Long customerFromId);

    @Query(value = "SELECT transaction_id from rel_customer_transaction where customer_to_id = :customerToId", nativeQuery = true)
    List<Long> getTransactionIdsFromCustomerToId(@Param("customerToId") Long customerToId);
}
