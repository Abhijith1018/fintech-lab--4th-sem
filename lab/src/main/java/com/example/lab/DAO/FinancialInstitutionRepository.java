package com.example.lab.DAO;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.lab.entity.FIN_INSTITUTIONS;

public interface FinancialInstitutionRepository extends JpaRepository<FIN_INSTITUTIONS, Long>{
    @Query("SELECT c FROM FIN_INSTITUTIONS c where c.name like :BankName")
    FIN_INSTITUTIONS findIdByName(@Param("BankName") String BankName);
}

