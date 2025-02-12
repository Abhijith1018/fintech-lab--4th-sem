package com.example.lab.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.lab.entity.User;
public interface UserRepository extends JpaRepository<User, Long> {
}

