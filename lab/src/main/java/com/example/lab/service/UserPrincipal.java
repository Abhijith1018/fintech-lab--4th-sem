package com.example.lab.service;


import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.lab.entity.CUST_SIGNIN;

public class UserPrincipal implements UserDetails{
    private CUST_SIGNIN cust_SIGNIN;

    UserPrincipal(CUST_SIGNIN cust_SIGNIN)
    {
        this.cust_SIGNIN = cust_SIGNIN;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // TODO Auto-generated method stub
        return Collections.singleton(new SimpleGrantedAuthority("USER"));
    }

    @Override
    public String getPassword() {
        return cust_SIGNIN.getPassword();
    }

    @Override
    public String getUsername() {
        return cust_SIGNIN.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}

