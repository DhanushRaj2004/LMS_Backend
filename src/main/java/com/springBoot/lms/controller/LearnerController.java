package com.springBoot.lms.controller;

import com.springBoot.lms.model.Learner;
import com.springBoot.lms.service.LearnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class LearnerController {

    @Autowired
    private LearnerService learnerService;
    /*
    path:https://localhost:8080/api/learner/add
     */
    @PostMapping("learner/add")
    public ResponseEntity<?> insertLearner(@RequestBody Learner learner){
        return ResponseEntity.status(HttpStatus.OK).body(learnerService.insertLearner(learner));
    }

    @GetMapping("learner/getAll")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(learnerService.getAll());
    }

    @DeleteMapping("learner/delete/{id}")
    public ResponseEntity<?> deleteLearner(@PathVariable int id){
        learnerService.deleteLearner(id);
        return ResponseEntity.status(HttpStatus.OK).body("Successfully Deleted");
    }

    @GetMapping("learner/get/{id}")
    public ResponseEntity<?> getLearner(@PathVariable int id){
        return ResponseEntity.status(HttpStatus.OK).body(learnerService.getLearner(id));
    }

    @PutMapping("learner/update/{id}")
    public ResponseEntity<?> updateLearner(@PathVariable int id, @RequestBody Learner learner){
        return ResponseEntity.status(HttpStatus.OK).body(learnerService.updateLearner(id,learner));
    }
}
