package net.themis.eclass.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.themis.eclass.config.JwtUtil;
import net.themis.eclass.model.Course;
import net.themis.eclass.model.DAOUser;
import net.themis.eclass.model.UserDTO;
import net.themis.eclass.repository.CourseRepository;
import net.themis.eclass.repository.UserRepository;
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

@Data
@AllArgsConstructor
@NoArgsConstructor
@RestController
public class TeachStudentController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private UserService userService;

    @Lazy
    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @RequestMapping(value="/get-all-courses" ,method= RequestMethod.GET )
    public List<Course> getAllCourses(){
        return courseService.getAllCourses();
    }

    @RequestMapping(value="/get-course-by-name" ,method= RequestMethod.GET )
    public ResponseEntity<Course> getCourseByName(@RequestParam(value = "name") String name){
        return new ResponseEntity<Course>(courseService.findByCourseName(name), HttpStatus.OK );
    }

    @RequestMapping(value="/home" ,method= RequestMethod.GET )
    public DAOUser home(){
        UserDetails userDetailService = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String name = userDetailService.getUsername();

        DAOUser user = userService.findByUsername(name);

        return user;
    }

    @RequestMapping(value="/update-profile" ,method= RequestMethod.PUT )
    public ResponseEntity<?> getCourseStudents(@RequestBody UserDTO user){

        UserDetails userDetailService = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetailService.getUsername();

        String pass = user.getPassword();
        user.setPassword(bcryptEncoder.encode(pass));

        userService.updateStudent(user.getUsername(),user.getPassword(),user.getFirstName(),user.getLastName()
                ,user.getEmail(),username);

        return new ResponseEntity<DAOUser>(userService.findByUsername(user.getUsername()), HttpStatus.OK );
    }

}
