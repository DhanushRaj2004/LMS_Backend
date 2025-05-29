package com.springBoot.lms.service;

import com.springBoot.lms.model.NUser;
import com.springBoot.lms.repository.NUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private NUserRepository nUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //Fetch User by username
        NUser nUser = nUserRepository.getByUserName(username);

        //User Implements UserDetails so User requires
        // username,password,Collection of GrantedAuthority Interface as a parameter so we can
        //use SimpleGrantedAuthority class which implements GrantedAuthority
        return new User(nUser.getUsername(),
                nUser.getPassword(),
                List.of(new SimpleGrantedAuthority(nUser.getRole())));
    }
}
