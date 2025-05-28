package com.springBoot.lms.service;

import com.springBoot.lms.exception.ResourseNotFoundException;
import com.springBoot.lms.model.Course;
import com.springBoot.lms.model.Learner;
import com.springBoot.lms.model.LearnerCourse;
import com.springBoot.lms.model.Review;
import com.springBoot.lms.repository.CourseRepository;
import com.springBoot.lms.repository.LearnerCourseRepository;
import com.springBoot.lms.repository.LearnerRepository;
import com.springBoot.lms.repository.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    private ReviewRepository reviewRepository;
    private LearnerRepository learnerRepository;
    private CourseRepository courseRepository;
    private LearnerCourseRepository learnerCourseRepository;

    public ReviewService(ReviewRepository reviewRepository,
                         LearnerRepository learnerRepository,
                         CourseRepository courseRepository,
                         LearnerCourseRepository learnerCourseRepository) {
        this.reviewRepository = reviewRepository;
        this.learnerRepository = learnerRepository;
        this.courseRepository = courseRepository;
        this.learnerCourseRepository = learnerCourseRepository;
    }

    public Review addReview(int learnerId, int courseId, Review review) {
        //fetch Learner by learner_id
//        Learner learner = learnerRepository.findById(learnerId)
//                .orElseThrow(() -> new ResourseNotFoundException("Learner Not found"));
//
////        fetch Course by course_id
//        Course course = courseRepository.findById(courseId)
//                .orElseThrow(() -> new ResourseNotFoundException("Course Not found"));

        //Check learner enrolled or not
        LearnerCourse learnerCourse = learnerCourseRepository.getByJPQL(learnerId,courseId)
                .orElseThrow(() -> new ResourseNotFoundException("Learner didn't enrolled any course"));

        //Set LearnerCoure to review
        review.setLearnerCourse(learnerCourse);

        //Save to Review Table
        return reviewRepository.save(review);
    }

    public List<Review> getReviewByRating(String rating) {

        if(reviewRepository.getByRating(rating).isEmpty()) {
            throw new ResourseNotFoundException("No Review greater than given rating");
        }
        return reviewRepository.getByRating(rating);
    }
}
