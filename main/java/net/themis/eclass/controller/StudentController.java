package net.themis.eclass.controller;

import net.themis.eclass.dto.CourseDTO;
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
    public ResponseEntity<CourseDTO> addCourse(@PathVariable String name){
        userService.enrollCourse(name);
       return new ResponseEntity<CourseDTO>(HttpStatus.CREATED);
    }

    @PostMapping("/courses/unenroll/{name}")
    public ResponseEntity<?> removeCourse(@PathVariable String name){
        userService.unenrollCourse(name);
        return new ResponseEntity<>(HttpStatus.OK );
    }

    @GetMapping("/my-courses")
    public List<CourseDTO> getMyCourses(){

        return userService.getMyCourses();
    }

}
