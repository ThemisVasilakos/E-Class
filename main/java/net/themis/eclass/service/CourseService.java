package net.themis.eclass.service;

import net.themis.eclass.model.Course;

import java.util.List;

public interface CourseService {
    Course saveCourse(Course course);
    List<Course> getAllCourses();
    Course findByCourseName(String name);
    List<Course> findMyCourses(String name);
}
