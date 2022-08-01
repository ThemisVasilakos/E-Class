package net.themis.eclass.controller;

import net.themis.eclass.model.Course;
import net.themis.eclass.model.DAOUser;
import net.themis.eclass.service.CourseService;
import net.themis.eclass.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value="/teacher")
public class TeacherController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private UserService userService;

    @PostMapping("/courses/create")
    public ResponseEntity<Course> createCourse(@RequestBody Course course){
        return new ResponseEntity<Course>(courseService.saveCourse(course), HttpStatus.CREATED );
    }

    @GetMapping("/students/get-all")
    public List<DAOUser> getAllStudents(){
        return userService.findByRole("ROLE_USER");
    }

    @GetMapping("/courses/students/{name}")
    public List<DAOUser> getCourseStudents(@PathVariable String name){
        return userService.getStudentsFromCourse(name);
    }

}
