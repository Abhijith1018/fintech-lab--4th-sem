package com.example.lab.DAO;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.lab.entity.CUST_SIGNIN;

public interface CustomerSignInRepository extends JpaRepository<CUST_SIGNIN, Long>{

    @Query("SELECT c FROM CUST_SIGNIN c where c.userName like :user_name")
    CUST_SIGNIN findByUserName(@Param("user_name") String user_name);
}

