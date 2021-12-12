package com.ing.bank.api.repository;

import com.ing.bank.api.dto.AddressDTO;
import com.ing.bank.api.entity.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<AddressEntity, Long> {

    /**
     *
     * @param postCode
     * @param number
     * @return
     */
    @Query(value = "SELECT * FROM address WHERE post_code = :postCode AND number = :number AND customer_id = :customerId limit 1", nativeQuery = true)
    AddressEntity verifyIfAddressExistsByPostCodeAndNumberAndCustomerId(@Param("postCode") String postCode, @Param("number") String number, @Param("customerId") Long customerId);
}
