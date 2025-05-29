package com.springBoot.lms.repository;


import com.springBoot.lms.model.NUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface NUserRepository extends JpaRepository<NUser,Integer> {

    @Query("select u from NUser u where u.username = ?1")
    NUser getByUserName(String username);
}
