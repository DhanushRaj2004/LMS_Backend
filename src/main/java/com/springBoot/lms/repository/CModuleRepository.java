package com.springBoot.lms.repository;

import com.springBoot.lms.model.CModule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CModuleRepository extends JpaRepository<CModule,Integer> {

    @Query("select m from CModule m where m.course.id = ?1")
    CModule getModuleByCourse(int courseId);
}
