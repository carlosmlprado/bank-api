package com.ing.bank.api.repository;

import com.ing.bank.api.entity.TransactionEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {

    @Query(value = "SELECT amount FROM transaction WHERE id in (:transactionIds)", nativeQuery = true)
    List<Float> getAmountByTransactionId(@Param("transactionIds") String transactionIds);
}
