package com.ing.bank.api.repository;

import com.ing.bank.api.entity.UserTransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTransactionRepository extends JpaRepository<UserTransactionEntity, Long> {


}
