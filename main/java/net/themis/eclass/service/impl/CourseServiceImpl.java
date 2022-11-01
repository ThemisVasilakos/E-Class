package net.themis.eclass.service.impl;

import net.themis.eclass.dto.CourseDTO;
import net.themis.eclass.model.Course;
import net.themis.eclass.repository.CourseRepository;
import net.themis.eclass.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public CourseDTO saveCourse(CourseDTO courseDTO) {

        Course course = new Course();
        course.setCourseName(courseDTO.getCourseName());
        course.setCourseDesc(courseDTO.getCourseDesc());
        courseRepository.save(course);
        return courseDTO;
    }

    @Override
    public List<CourseDTO> getAllCourses() {

        List<Course> courses = courseRepository.findAll();
        List<CourseDTO> courseDTOS = new ArrayList<>();

        for(int i=0;i<courses.size();i++){
            CourseDTO tmp = new CourseDTO(courses.get(i));
            courseDTOS.add(tmp);
        }
        return courseDTOS;
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
