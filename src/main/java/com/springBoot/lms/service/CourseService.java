package com.springBoot.lms.service;

import com.springBoot.lms.model.Author;
import com.springBoot.lms.model.Course;
import com.springBoot.lms.repository.AuthorRepository;
import com.springBoot.lms.repository.CourseRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    private CourseRepository courseRepository;
    private AuthorRepository authorRepository;

    public CourseService(CourseRepository courseRepository, AuthorRepository authorRepository) {
        this.courseRepository = courseRepository;
        this.authorRepository = authorRepository;
    }

    public Course addCourse(String username, Course course) {

        //Fetch author by username
        Author author = authorRepository.getAuthorByUsername(username);

        System.out.println(author);
        //Set author to course
        course.setAuthor(author);

        return courseRepository.save(course);
    }

    public List<Course> getAllCourse(int page, int size) {
        Pageable pageable = PageRequest.of(page,size);
        return courseRepository.findAll(pageable).getContent();
    }

}
