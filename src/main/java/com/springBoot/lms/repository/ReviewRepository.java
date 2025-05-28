package com.springBoot.lms.repository;

import com.springBoot.lms.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review,Integer> {

    @Query("select r from Review r where r.rating > ?1")
    List<Review> getByRating(String rating);
}
