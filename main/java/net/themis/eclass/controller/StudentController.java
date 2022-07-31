package net.themis.eclass.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.themis.eclass.model.Course;
import net.themis.eclass.model.DAOUser;
import net.themis.eclass.repository.CourseRepository;
import net.themis.eclass.repository.UserRepository;
import net.themis.eclass.service.CourseService;
import net.themis.eclass.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RestController
public class StudentController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private UserService userService;

   @RequestMapping(value="/add-course" ,method= RequestMethod.POST )
    public ResponseEntity<Course> addCourse(@RequestParam(value="name") String courseName){

       UserDetails userDetailService = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
       String username = userDetailService.getUsername();

       DAOUser user = userService.findByUsername(username);

       Course course = courseService.findByCourseName(courseName);

       course.addStudents(user);

       return new ResponseEntity<Course>(courseService.saveCourse(course), HttpStatus.CREATED );
    }

    @RequestMapping(value="/remove-course" ,method= RequestMethod.POST )
    public ResponseEntity<Course> removeCourse(@RequestParam(value="name") String courseName){

        UserDetails userDetailService = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetailService.getUsername();

        Course course = courseService.findByCourseName(courseName);

        course.removeStudent(username);

        return new ResponseEntity<Course>(courseService.saveCourse(course), HttpStatus.CREATED );
    }

    @RequestMapping(value="/my-courses" ,method= RequestMethod.GET )
    public List<Course> getMyCourses(){

        UserDetails userDetailService = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetailService.getUsername();

        courseService.findMyCourses(username);

        return courseService.findMyCourses(username);
    }

}
