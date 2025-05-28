package com.springBoot.lms.service;

import com.springBoot.lms.exception.ResourseNotFoundException;
import com.springBoot.lms.model.CModule;
import com.springBoot.lms.model.Course;
import com.springBoot.lms.repository.CModuleRepository;
import com.springBoot.lms.repository.CourseRepository;
import org.springframework.stereotype.Service;

@Service
public class CModuleService {

    private CModuleRepository cModuleRepository;

    private CourseRepository courseRepository;

    public CModuleService(CModuleRepository cModuleRepository, CourseRepository courseRepository) {
        this.cModuleRepository = cModuleRepository;
        this.courseRepository = courseRepository;
    }

    public CModule addModule(CModule module, int course_id) {

        //fetch course by course_id
        Course course = courseRepository.findById(course_id)
                .orElseThrow(() -> new ResourseNotFoundException("Course Not found..,Id is invalid"));


        //Add to module table
        module.setCourse(course);

        return cModuleRepository.save(module);
    }

    public CModule getModuleByCourse(int courseId) {
        courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourseNotFoundException("Course id is invalid"));
        return cModuleRepository.getModuleByCourse(courseId);
    }
}
