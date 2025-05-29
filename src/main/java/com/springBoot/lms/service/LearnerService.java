package com.springBoot.lms.service;

import com.springBoot.lms.exception.ResourseNotFoundException;
import com.springBoot.lms.model.Learner;
import com.springBoot.lms.model.NUser;
import com.springBoot.lms.repository.LearnerRepository;
import com.springBoot.lms.repository.NUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LearnerService {

    private LearnerRepository learnerRepository;
    private NUserService nUserService;

    public LearnerService(LearnerRepository learnerRepository, NUserService nUserService) {
        this.learnerRepository = learnerRepository;
        this.nUserService = nUserService;
    }

    public Learner insertLearner(Learner learner) {

        //Get User in Learner
        NUser user = learner.getUser();

        //Set role for user
        user.setRole("LEARNER");

        //Sign up user
        user = nUserService.addUser(user);

        //Attach this user back to learner
        learner.setUser(user);

        return learnerRepository.save(learner);
    }
    
    public List<Learner> getAll() {
         return learnerRepository.findAll();
    }

    public void deleteLearner(int id) {
        learnerRepository.findById(id)
                .orElseThrow(() -> new ResourseNotFoundException("Id is invalid"));
        learnerRepository.deleteById(id);
    }

    public Learner getLearner(int id) {
        return learnerRepository.findById(id).
                orElseThrow(() -> new ResourseNotFoundException("Id is invalid"));
    }

    public Learner updateLearner(int id, Learner learner) {

        Learner dbLearner = learnerRepository.findById(id)
                .orElseThrow(() -> new ResourseNotFoundException("Id is invalid"));

        if(learner.getName() != null)
            dbLearner.setName(learner.getName());
        if(learner.getContact() != null)
            dbLearner.setContact(learner.getContact());

        return learnerRepository.save(dbLearner);
    }

    public Learner getLearnerByUser(String username) {
        return learnerRepository.getLearnerByUser(username);
    }
}
