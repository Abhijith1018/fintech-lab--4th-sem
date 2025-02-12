package com.example.lab.DAO;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.lab.entity.CUST_DETAILS;

public interface CustomerDetailsRepository extends JpaRepository<CUST_DETAILS, Long> {
    @Query("SELECT MAX(id) FROM CUST_DETAILS")
    public Long findMaxCustId();

    @Query("SELECT c from CUST_DETAILS c where c.id = :cust_id ORDER BY c.idfr desc LIMIT 1")
    public CUST_DETAILS findCustomerRecord(@Param("cust_id") Long cust_id);
}

