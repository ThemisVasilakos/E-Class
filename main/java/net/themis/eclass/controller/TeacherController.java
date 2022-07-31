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
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RestController
public class TeacherController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private UserService userService;

    @RequestMapping(value="/create-course" ,method= RequestMethod.POST )
    public ResponseEntity<Course> createCourse(@RequestBody Course course){
        return new ResponseEntity<Course>(courseService.saveCourse(course), HttpStatus.CREATED );
    }

    @RequestMapping(value="/get-all-students" ,method= RequestMethod.GET )
    public List<DAOUser> getAllStudents(){
        String role = "ROLE_USER";
        return userService.findByRole(role);
    }

    @RequestMapping(value="/get-course-students" ,method= RequestMethod.GET )
    public List<DAOUser> getCourseStudents(@RequestParam(value="course-name") String courseName){
        return userService.getStudentsFromCourse(courseName);
    }

}
