package com.springBoot.lms;

import com.springBoot.lms.exception.ResourseNotFoundException;
import com.springBoot.lms.model.Course;
import com.springBoot.lms.model.Learner;
import com.springBoot.lms.model.LearnerCourse;
import com.springBoot.lms.repository.CourseRepository;
import com.springBoot.lms.repository.LearnerCourseRepository;
import com.springBoot.lms.repository.LearnerRepository;
import com.springBoot.lms.service.LearnerCourseService;
import jakarta.persistence.ManyToOne;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
public class LearnerCourseServiceTest {

    @InjectMocks
    private LearnerCourseService learnerCourseService;
    @Mock
    private LearnerRepository learnerRepository;
    @Mock
    private CourseRepository courseRepository;
    @Mock
    private LearnerCourseRepository learnerCourseRepository;

    private Learner learner;
    private Course course;
    private LearnerCourse learnerCourse;

    //@Test
    public void getCourseByLearnerTest(){

        //Expected output
        Course course = new Course();
        course.setId(9);
        course.setCourseTitle("Java core");
        course.setCredits(90);
        List<Course> expectedList = List.of(course);

        //Actual output
        List<Course> actualList = learnerCourseService.getCourseByLearner(1);

        assertEquals(expectedList,actualList);
    }

    @BeforeEach
    public void init(){
        learner = new Learner();
        learner.setId(22);
        learner.setName("Mani");
        learner.setContact("9877852562");
        System.out.println(learner);

        course = new Course();
        course.setId(32);
        course.setCourseTitle("Python");
        course.setCredits(23);
        System.out.println(course);

        learnerCourse = new LearnerCourse();
        learnerCourse.setId(2);
        learnerCourse.setCoupon("IYER87");
        learnerCourse.setEnrollDate(LocalDate.now());
        learnerCourse.setLearner(learner);
        learnerCourse.setCourse(course);
        System.out.println(learnerCourse);
    }

    @Test
    public void getCourseByLearnerMockTest(){

        List<Course> expectedList = List.of(course);

        when(learnerRepository.findById(22)).thenReturn(Optional.of(learner));

        when(learnerCourseRepository.getCourseByLearner(22)).thenReturn(expectedList);


        //Actual output
        List<Course> actualList = learnerCourseRepository.getCourseByLearner(22);

        assertEquals(expectedList,actualList);

    }

    @Test
    public void enrollLearnerMockTest(){

        LearnerCourse lc = new LearnerCourse();
        when(learnerRepository.findById(22)).thenReturn(Optional.of(learner));
        when(courseRepository.findById(32)).thenReturn(Optional.of(course));
        when(learnerCourseRepository.save(lc)).thenReturn(learnerCourse);

        assertEquals(learnerCourse,
                learnerCourseService.enrollLearner(22,32,lc));

        //use case for invalid learner id
        ResourseNotFoundException e = assertThrows(ResourseNotFoundException.class,
                () -> learnerCourseService.enrollLearner(20,32,lc));
        assertEquals("Lerner id not found....".toLowerCase(),e.getMessage().toLowerCase());

        //use case for invalid course id
        e = assertThrows(ResourseNotFoundException.class,
                () -> learnerCourseService.enrollLearner(22,30,lc));
        assertEquals("Course id not found....".toLowerCase(),e.getMessage().toLowerCase());

    }

    @AfterEach
    public void afterTest(){
        learner = null;
        System.out.println(learner);
        course = null;
        System.out.println(course);
        learnerCourse = null;
        System.out.println(learnerCourse);
    }


}
