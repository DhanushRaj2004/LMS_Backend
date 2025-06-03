package com.springBoot.lms.service;

import com.springBoot.lms.dto.LearnerDto;
import com.springBoot.lms.exception.ResourseNotFoundException;
import com.springBoot.lms.model.Course;
import com.springBoot.lms.model.Learner;
import com.springBoot.lms.model.LearnerCourse;
import com.springBoot.lms.repository.CourseRepository;
import com.springBoot.lms.repository.LearnerCourseRepository;
import com.springBoot.lms.repository.LearnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class LearnerCourseService {

    private LearnerRepository learnerRepository;
    private CourseRepository courseRepository;
    private LearnerCourseRepository learnerCourseRepository;

    @Autowired
    private LearnerDto learnerDto;

    public LearnerCourseService(LearnerRepository learnerRepository, CourseRepository courseRepository, LearnerCourseRepository learnerCourseRepository) {
        this.learnerRepository = learnerRepository;
        this.courseRepository = courseRepository;
        this.learnerCourseRepository = learnerCourseRepository;
    }

    public LearnerCourse enrollLearner(int learnerId, int courseId, LearnerCourse learnerCourse) {

        //fetch Learner by learner_id
        Learner learner = learnerRepository.findById(learnerId)
                .orElseThrow(() -> new ResourseNotFoundException("Lerner id not found...."));

        //fetch Course by course_id
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourseNotFoundException("Course id not found...."));
        //set today date to enrolledDate
        learnerCourse.setEnrollDate(LocalDate.now());

        //set learner and course
        learnerCourse.setLearner(learner);
        learnerCourse.setCourse(course);

        //Save to LearnerCourse
        return learnerCourseRepository.save(learnerCourse);
    }


    public List<LearnerDto> getLearnerByCourseId(int courseId) {

        courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourseNotFoundException("Course id is invalid"));

        List<Learner> learner = learnerCourseRepository.getLearnerByCourseId(courseId);
        return learnerDto.getLearnerDto(learner);
    }

    public List<Course> getCourseByLearner(int learnerId) {
        learnerCourseRepository.findById(learnerId)
                .orElseThrow(() -> new ResourseNotFoundException("Learner didn't enrolled any course"));
        return learnerCourseRepository.getCourseByLearner(learnerId);
    }
}
