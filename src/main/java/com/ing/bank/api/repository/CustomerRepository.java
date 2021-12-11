package com.ing.bank.api.repository;

import com.ing.bank.api.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {

    @Query("SELECT c FROM CustomerEntity c WHERE bsn = :bsn")
    CustomerEntity findCustomerByBsn(@Param("bsn") String bsn);

}
