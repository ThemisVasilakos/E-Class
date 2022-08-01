package net.themis.eclass.service;

import net.themis.eclass.model.Course;
import net.themis.eclass.repository.CourseRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class CourseServiceTest {

    @Autowired
    private CourseRepository courseRepository;

    @Test
    public void saveCourse(){
        Course course = Course.builder().courseName("Databases").courseDesc("Intro to MySQL").build();

        courseRepository.save(course);
    }

    @Test
    public void getAllCourses(){
        List<Course> courses = courseRepository.findAll();
        System.out.println(courses);
    }

    @Test
    public void findByCourseName(){
        Course course = courseRepository.findByCourseName("Java");
        System.out.println(course);
    }

    @Test
    public void findMyCourses(){
        List<Course> courses = courseRepository.findMyCourses("ragnar");
        System.out.println(courses);
    }
}