package com.springBoot.lms.controller;

import com.springBoot.lms.model.Learner;
import com.springBoot.lms.model.LearnerCourse;
import com.springBoot.lms.service.LearnerCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LearnerCourseController {

    @Autowired
    private LearnerCourseService learnerCourseService;

    /*
    * AIM : to enroll the learner to course
    * PATH : /api/learner/enroll/course/{learner_id}/{couse_id}
    * METHOD : POST
    * Input : PathVariable learner_id,course_id and RequestBody of LearnerCourse
    * Response : Learner enrolled to course
    * */

    @PostMapping("/api/learner/enroll/course/{learner_id}/{course_id}")
    public ResponseEntity<?> enrollLearner(@PathVariable int learner_id, @PathVariable int course_id,
                                                       @RequestBody LearnerCourse learnerCourse){
        return ResponseEntity.status(HttpStatus.OK)
                .body(learnerCourseService.enrollLearner(learner_id,course_id,learnerCourse));
    }

    /*
     * AIM : Get Learner by CourseId
     * PATH : /api/learner/enroll/course/{couse_id}
     * METHOD : GET
     * Input : PathVariable course_id
     * Response : List<Learner>
     */

    @GetMapping("/api/learner/enroll/course/{course_id}")
    public ResponseEntity<?> getLearnerByCourseId(@PathVariable int course_id){
        return ResponseEntity.status(HttpStatus.OK).body(learnerCourseService.getLearnerByCourseId(course_id));
    }

    /*
    * AIM : Get All course taken by a learner
    * PATH : /api/learner/course/{learner_id}
    * METHOD : GET
    * Input : PathVariable course_id
    * Response : List<Course>
    */
    @GetMapping("/api/learner/course/{learner_id}")
    public ResponseEntity<?> getCourseByLearner(@PathVariable int learner_id){
        return ResponseEntity.status(HttpStatus.OK).
                body(learnerCourseService.getCourseByLearner(learner_id));
    }

}
