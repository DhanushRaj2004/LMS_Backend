package com.springBoot.lms.repository;

import com.springBoot.lms.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author,Integer> {

    @Query("select a from Author a where a.user.username = ?1")
    Author getAuthorByUsername(String username);
}
