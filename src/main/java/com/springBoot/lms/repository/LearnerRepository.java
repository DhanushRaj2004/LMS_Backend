package com.springBoot.lms.repository;

import com.springBoot.lms.model.Learner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LearnerRepository extends JpaRepository<Learner,Integer> {

    @Query("select l from Learner l where l.user.username = ?1")
    Learner getLearnerByUser(String username);
}
