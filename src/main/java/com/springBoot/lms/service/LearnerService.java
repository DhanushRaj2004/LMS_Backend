package com.springBoot.lms.service;

import com.springBoot.lms.exception.ResourseNotFoundException;
import com.springBoot.lms.model.Learner;
import com.springBoot.lms.repository.LearnerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LearnerService {

    private LearnerRepository learnerRepository;

    public LearnerService(LearnerRepository learnerRepository) {
        this.learnerRepository = learnerRepository;
    }

    public Learner insertLearner(Learner learner) {
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
}
