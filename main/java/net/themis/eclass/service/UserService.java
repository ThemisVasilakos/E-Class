package net.themis.eclass.service;

import net.themis.eclass.model.Course;
import net.themis.eclass.model.DAOUser;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserService {
    DAOUser findByUserName();
    List<DAOUser> findByRole(String username);
    List<DAOUser> getStudentsFromCourse(String courseName);
    @Transactional
    Course enrollCourse(String courseName);
    @Transactional
    Course unenrollCourse(String courseName);
    List<Course> getMyCourses();
    @Transactional
    int updateStudent(String username,String password,String firstName,String lastName,String email,String loggedUser);
}
