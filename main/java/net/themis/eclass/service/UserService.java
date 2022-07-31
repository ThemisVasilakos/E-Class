package net.themis.eclass.service;

import net.themis.eclass.model.DAOUser;

import java.util.List;

public interface UserService {
    DAOUser findByUsername(String username);
    List<DAOUser> findByRole(String username);
    List<DAOUser> getStudentsFromCourse(String courseName);
    int updateStudent(String username,String password,String firstName,String lastName,String email,String loggedUser);
}
