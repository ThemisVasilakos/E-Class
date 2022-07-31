package net.themis.eclass.service.impl;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.themis.eclass.model.DAOUser;
import net.themis.eclass.repository.UserRepository;
import net.themis.eclass.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public DAOUser findByUsername(String username) {
        return userRepository.findByUsername(username);
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
    public int updateStudent(String username, String password, String firstName, String lastName, String email, String loggedUser) {
        userRepository.updateStudent(username,password,firstName,lastName,email,loggedUser);
        return 0;
    }
}
