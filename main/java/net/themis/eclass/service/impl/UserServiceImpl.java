package net.themis.eclass.service.impl;

import net.themis.eclass.dto.CourseDTO;
import net.themis.eclass.model.Course;
import net.themis.eclass.model.DAOUser;
import net.themis.eclass.dto.UserDTO;
import net.themis.eclass.repository.CourseRepository;
import net.themis.eclass.repository.UserRepository;
import net.themis.eclass.service.CourseService;
import net.themis.eclass.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseService courseService;

    @Autowired
    private CourseRepository courseRepository;

    @Lazy
    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Override
    public DAOUser findByUserName() {
        UserDetails userDetailService = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String name = userDetailService.getUsername();

        return userRepository.findByUsername(name);
    }

    @Override
    public List<DAOUser> findByRole(String username) {
        return userRepository.findByRole(username);
    }

    @Override
    public List<DAOUser> getStudentsFromCourse(String courseName) {
        return userRepository.getStudentsFromCourse(courseName);
    }

    @Override
    @Transactional
    public CourseDTO enrollCourse(String courseName) {
        DAOUser user = this.findByUserName();
        Course course = courseService.findByCourseName(courseName);
        course.addStudents(user);

        courseRepository.save(course);

        CourseDTO courseDTO = new CourseDTO(course);
        return courseDTO;
    }

    @Override
    @Transactional
    public void unenrollCourse(String courseName) {
        UserDetails userDetailService = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetailService.getUsername();

        Course course = courseService.findByCourseName(courseName);

        course.removeStudent(username);
    }

    @Override
    public List<CourseDTO> getMyCourses() {
        UserDetails userDetailService = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetailService.getUsername();

        List<Course> courses = courseService.findMyCourses(username);

        List<CourseDTO> courseDTOList = new ArrayList<>();

        for(int i=0;i<courses.size();i++){
            CourseDTO tmp = new CourseDTO(courses.get(i));
            courseDTOList.add(tmp);
        }

        return courseDTOList;
    }

    @Override
    @Transactional
    public int updateStudent(UserDTO userDTO, String username, String password, String firstName, String lastName, String email) {
        UserDetails userDetailService = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username1 = userDetailService.getUsername();

        String pass = userDTO.getPassword();
        userDTO.setPassword(bcryptEncoder.encode(pass));

        userRepository.updateStudent(username,userDTO.getPassword(),firstName,lastName,email,username1);
        return 0;
    }
}
