package com.springBoot.lms.controller;

import com.springBoot.lms.model.Course;
import com.springBoot.lms.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/course")
public class CourseController {

    @Autowired
    private CourseService courseService;
    /*
    * AIM : To add Course, Only done by Author(Admin)
    * Path : /api/course/add
    * Method : POST
    * Input : Course as RequestBody
    */
    @PostMapping("add")
    public ResponseEntity<?> addCourse(@RequestBody Course course){
        courseService.addCourse(course);
        return ResponseEntity.status(HttpStatus.OK).body("Successfully added");
    }


}
