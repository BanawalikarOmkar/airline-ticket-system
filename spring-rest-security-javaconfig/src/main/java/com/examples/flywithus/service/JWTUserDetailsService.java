package com.examples.flywithus.service;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.examples.flywithus.dao.UserInfoDAO;
import com.examples.flywithus.entity.UserInfo;

@Service
public class JWTUserDetailsService implements UserDetailsService {

    @Autowired
    private UserInfoDAO userInfoDAO;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserInfo user = userInfoDAO.getActiveUser(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        GrantedAuthority authority = new SimpleGrantedAuthority(user.getRole());
        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
                Arrays.asList(authority));
        
    }

    

}
