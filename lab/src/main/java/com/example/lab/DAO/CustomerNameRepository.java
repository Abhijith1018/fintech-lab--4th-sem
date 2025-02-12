package com.example.lab.DAO;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.lab.entity.CUST_NAME;

public interface CustomerNameRepository extends JpaRepository<CUST_NAME, Long>{

}

