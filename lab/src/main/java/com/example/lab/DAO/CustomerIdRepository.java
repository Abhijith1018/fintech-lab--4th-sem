package com.example.lab.DAO;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.lab.entity.CUST_ID;

public interface CustomerIdRepository extends JpaRepository<CUST_ID, Long>{

}

