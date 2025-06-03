package com.springBoot.lms.controller;

import com.springBoot.lms.model.Course;
import com.springBoot.lms.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

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
    public ResponseEntity<?> addCourse(Principal principal,@RequestBody Course course){
        String username = principal.getName();
        courseService.addCourse(username,course);
        return ResponseEntity.status(HttpStatus.OK).body("Successfully added");
    }

    @GetMapping("/get-all")
    public List<Course> getAllCourse(@RequestParam(name = "page",
                                                 required = false,
                                                 defaultValue = "0") int page,
                                     @RequestParam(name = "size",
                                             required = false,
                                             defaultValue = "100000") int size){
        return courseService.getAllCourse(page,size);
    }

}
