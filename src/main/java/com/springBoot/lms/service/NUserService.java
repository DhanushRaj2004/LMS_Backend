package com.springBoot.lms.service;

import com.springBoot.lms.model.NUser;
import com.springBoot.lms.repository.NUserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class NUserService {

    private NUserRepository nUserRepository;
    private PasswordEncoder passwordEncoder;

    public NUserService(NUserRepository nUserRepository, PasswordEncoder passwordEncoder) {
        this.nUserRepository = nUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public NUser addUser(NUser nUser) {

        //Encrypt a password
        String plainPass = nUser.getPassword();
        String encodedPass = passwordEncoder.encode(plainPass);
        nUser.setPassword(encodedPass);

        //Save NUser to DB with encoded password
        return nUserRepository.save(nUser);
    }
}
