package com.ing.bank.api.repository;

import com.ing.bank.api.entity.CustomerTransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTransactionRepository extends JpaRepository<CustomerTransactionEntity, Long> {


}
