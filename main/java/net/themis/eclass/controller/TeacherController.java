package net.themis.eclass.controller;

import net.themis.eclass.dto.AnnouncementDTO;
import net.themis.eclass.dto.CourseDTO;
import net.themis.eclass.model.Course;
import net.themis.eclass.model.DAOUser;
import net.themis.eclass.service.CourseService;
import net.themis.eclass.service.UserService;
import net.themis.eclass.service.impl.AnnouncementServiceImpl;
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

    @Autowired
    private AnnouncementServiceImpl announcementService;

    @PostMapping("/courses/new")
    public ResponseEntity<CourseDTO> createCourse(@RequestBody CourseDTO courseDTO){
        return new ResponseEntity<CourseDTO>(courseService.saveCourse(courseDTO), HttpStatus.CREATED );
    }

    @GetMapping("/courses/students")
    public List<DAOUser> getAllStudents(){
        return userService.findByRole("ROLE_STUDENT");
    }

    @GetMapping("/courses/{name}/students")
    public List<DAOUser> getCourseStudents(@PathVariable String name){
        return userService.getStudentsFromCourse(name);
    }

    @PostMapping("/courses/{courseId}/announcements/new")
    public ResponseEntity<AnnouncementDTO> createAnnouncement(@RequestBody AnnouncementDTO announcementDTO,@PathVariable Long courseId){
        return new ResponseEntity<AnnouncementDTO>(announcementService.createAnnouncement(announcementDTO,courseId), HttpStatus.CREATED );
    }

}
