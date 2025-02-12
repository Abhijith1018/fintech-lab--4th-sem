package com.example.lab.DAO;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.lab.DTO.TypeValue;
import com.example.lab.entity.CUST_ADDRESS;

public interface CustomerAddressRepository extends JpaRepository<CUST_ADDRESS, Long>{
    @Query(value = "select c.CSTCL_TYP_VALUE, a.CSTADD_VALUE from CUST_ADDRESS a JOIN CUST_CL c on a.cstadd_cls_id = c.cstcl_id where a.cst_id = :cust_idfr", nativeQuery = true)
    List<TypeValue> findCustomerAddress(@Param("cust_idfr") Long cust_idfr);

    @Query(value = "SELECT CSTADD_VALUE FROM CUST_ADDRESS WHERE CSTADD_IDFR = :idfr and CSTADD_CLS_ID = (SELECT CSTCL_ID FORM CUST_CL WHERE CSTCL_TYP_VAlUE = :type)", nativeQuery = true)
    public String findCustomerTerritory(@Param("idfr") Long idfr, @Param("type") String type);

}

