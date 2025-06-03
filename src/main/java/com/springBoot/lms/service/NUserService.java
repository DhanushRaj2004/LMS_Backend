package com.springBoot.lms.service;

import com.springBoot.lms.model.Learner;
import com.springBoot.lms.model.NUser;
import com.springBoot.lms.repository.LearnerRepository;
import com.springBoot.lms.repository.NUserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class NUserService {

    private NUserRepository nUserRepository;
    private PasswordEncoder passwordEncoder;
    private LearnerRepository learnerRepository;

    public NUserService(NUserRepository nUserRepository, PasswordEncoder passwordEncoder, LearnerRepository learnerRepository) {
        this.nUserRepository = nUserRepository;
        this.passwordEncoder = passwordEncoder;
        this.learnerRepository = learnerRepository;
    }

    public NUser addUser(NUser nUser) {

        //Encrypt a password
        String plainPass = nUser.getPassword();
        String encodedPass = passwordEncoder.encode(plainPass);
        nUser.setPassword(encodedPass);

        //Save NUser to DB with encoded password
        return nUserRepository.save(nUser);
    }

    public Object getUserInfo(String username) {
        NUser user = nUserRepository.getByUserName(username);

        switch (user.getRole().toUpperCase()){
            case "LEARNER":
                System.out.println("I am in switch Learner");
                Learner learner = learnerRepository.getLearnerByUser(username);
                return learner;
            case "AUTHOR":
                return null;
            case "EXECUTIVE":
                return null;
            default:
                return null;
        }

    }
}
