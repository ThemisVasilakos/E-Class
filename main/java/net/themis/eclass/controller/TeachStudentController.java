package net.themis.eclass.controller;

import net.themis.eclass.model.Course;
import net.themis.eclass.model.DAOUser;
import net.themis.eclass.model.UserDTO;
import net.themis.eclass.service.CourseService;
import net.themis.eclass.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/eclass")
public class TeachStudentController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private UserService userService;

    @Lazy
    @Autowired
    private PasswordEncoder bcryptEncoder;

    @GetMapping("/courses/get-all")
    public List<Course> getAllCourses(){
        return courseService.getAllCourses();
    }

    @GetMapping("/courses/{name}")
    public ResponseEntity<Course> getCourseByName(@PathVariable String name){
        return new ResponseEntity<Course>(courseService.findByCourseName(name), HttpStatus.OK );
    }

    @GetMapping("/home")
    public DAOUser home(){

        DAOUser user = userService.findByUserName();

        return user;
    }

    @PutMapping("/update-profile")
    public ResponseEntity<?> getCourseStudents(@RequestBody UserDTO user){

        UserDetails userDetailService = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetailService.getUsername();

        String pass = user.getPassword();
        user.setPassword(bcryptEncoder.encode(pass));

        userService.updateStudent(user.getUsername(),user.getPassword(),user.getFirstName(),user.getLastName()
                ,user.getEmail(),username);

        return new ResponseEntity<DAOUser>(userService.findByUserName(), HttpStatus.OK );
    }

}
