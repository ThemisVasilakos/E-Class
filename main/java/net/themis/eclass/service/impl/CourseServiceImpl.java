package net.themis.eclass.service.impl;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.themis.eclass.model.Course;
import net.themis.eclass.repository.CourseRepository;
import net.themis.eclass.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public Course saveCourse(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public Course findByCourseName(String name) {
        return courseRepository.findByCourseName(name);
    }

    @Override
    public List<Course> findMyCourses(String name) {
        return courseRepository.findMyCourses(name);
    }
}
