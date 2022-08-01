package net.themis.eclass.controller;

import net.themis.eclass.model.Course;
import net.themis.eclass.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping(value="/student")
public class StudentController {


    @Autowired
    private UserService userService;
    //
    @PostMapping("/courses/{name}/enroll")
    public ResponseEntity<Course> addCourse(@PathVariable String name){
        userService.enrollCourse(name);
       return new ResponseEntity<Course>(HttpStatus.CREATED);
    }

    @PostMapping("/courses/unenroll/{name}")
    public ResponseEntity<Course> removeCourse(@PathVariable String name){
        userService.unenrollCourse(name);
        return new ResponseEntity<Course>(HttpStatus.OK );
    }

    @GetMapping("/courses")
    public List<Course> getMyCourses(){

        return userService.getMyCourses();
    }

}
