package com.springBoot.lms.repository;

import com.springBoot.lms.model.Course;
import com.springBoot.lms.model.Learner;
import com.springBoot.lms.model.LearnerCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LearnerCourseRepository extends JpaRepository<LearnerCourse,Integer> {

//    @Query(value = "select * from learner_course where learner_id = ?1 and course_id = ?2",nativeQuery = true)
//    Optional<LearnerCourse> getByNativeQuery(int learnerId, int courseId);

    @Query("select lc from LearnerCourse lc where lc.learner.id = ?1 and lc.course.id = ?2")
    Optional<LearnerCourse> getByJPQL(int learnerId,int courseId);

    @Query("select lc.learner from LearnerCourse lc where lc.course.id = ?1")
    List<Learner> getLearnerByCourseId(int courseId);

    @Query("select lc.course from LearnerCourse lc where lc.learner.id = ?1")
    List<Course> getCourseByLearner(int learnerId);
}
