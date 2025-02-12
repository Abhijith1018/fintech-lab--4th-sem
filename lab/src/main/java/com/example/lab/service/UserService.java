package com.example.lab.service;


import java.util.List;
import java.util.Optional;

import com.example.lab.DAO.CustomerDetailsRepository;
import com.example.lab.DAO.CustomerSignInRepository;
import com.example.lab.DAO.UserRepository;
import com.example.lab.DTO.TypeValue;
import com.example.lab.entity.CUST_DETAILS;
import com.example.lab.entity.CUST_SIGNIN;
import com.example.lab.entity.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CustomerSignInRepository customerSignInRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        CUST_SIGNIN cust_SIGNIN = customerSignInRepository.findByUserName(username);
        if (cust_SIGNIN == null)
        {
            throw new UsernameNotFoundException("username not found!");
        }
        return new UserPrincipal(cust_SIGNIN);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    public User saveUser(User user) {
        return userRepository.save(user);
    }
}

