package com.example.lab.DAO;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.lab.entity.CUST_CL;

public interface CustomerClassificationRepository extends JpaRepository<CUST_CL, Long> {
    @Query("SELECT c FROM CUST_CL c where c.typeValue like :cls_type")
    CUST_CL findByType(@Param("cls_type") String cls_type);
}

