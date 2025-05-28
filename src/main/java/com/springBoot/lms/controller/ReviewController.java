package com.springBoot.lms.controller;

import com.springBoot.lms.model.Review;
import com.springBoot.lms.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/review")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    /*
    * AIM : Add Learner reviews
    * PATH : /api/review/add/{learner_id}/{course_id}
    * METHOD : POST
    * Input : learner_id and course_id as PathVariable,Review as RequestBody
    * Response : Learner review added to Review table
    */

    @PostMapping("/add/{learner_id}/{course_id}")
    public ResponseEntity<?> addReview(@PathVariable int learner_id,
                                            @PathVariable int course_id,
                                            @RequestBody Review review){
        return ResponseEntity.status(HttpStatus.OK)
                .body(reviewService.addReview(learner_id,course_id,review));
    }

    /*
    * AIM : Get review with rating > given rating
    * PARAM : We pass rating as RequestParam
    * PATH : /api/review/rating?rating = 3
    * METHOD : GET
    * Input : Rating as RequestParam
    * Response : List<Review>
    */
    @GetMapping("/rating")
    public ResponseEntity<?> getReviewByRating(@RequestParam String rating){
        return ResponseEntity.status(HttpStatus.OK).body(reviewService.getReviewByRating(rating));
    }
}
