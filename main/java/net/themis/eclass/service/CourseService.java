package net.themis.eclass.service;

import net.themis.eclass.dto.CourseDTO;
import net.themis.eclass.model.Course;

import java.util.List;

public interface CourseService {
    CourseDTO saveCourse(CourseDTO courseDTO);
    List<CourseDTO> getAllCourses();
    Course findByCourseName(String name);
    List<Course> findMyCourses(String name);
}
