package com.example.lab.DAO;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.lab.entity.CUST_POI;

public interface CustomerPoiRepository extends JpaRepository<CUST_POI, Long> {

}
