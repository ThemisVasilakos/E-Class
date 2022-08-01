package net.themis.eclass.service;

import net.themis.eclass.model.Course;
import net.themis.eclass.model.DAOUser;
import net.themis.eclass.model.UserDTO;

import java.util.List;

public interface UserService {
    DAOUser findByUserName();
    List<DAOUser> findByRole(String username);
    List<DAOUser> getStudentsFromCourse(String courseName);
    Course enrollCourse(String courseName);
    Course unenrollCourse(String courseName);
    List<Course> getMyCourses();
    int updateStudent(UserDTO userDTO, String username, String password, String firstName, String lastName, String email);
}
