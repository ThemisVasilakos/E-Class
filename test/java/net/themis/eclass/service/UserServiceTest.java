package net.themis.eclass.service;

import net.themis.eclass.model.DAOUser;
import net.themis.eclass.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void findByUsername(){
        DAOUser user = userRepository.findByUsername("themis");

        System.out.println(user);
    }

    @Test
    public void findByRole(){
        List<DAOUser> users = userRepository.findByRole("ROLE_USER");
        System.out.println(users);
    }

    @Test
    public void getStudentsFromCourse(){
        List<DAOUser> users = userRepository.getStudentsFromCourse("Algebra");
        System.out.println(users);
    }

    @Test
    public void updateStudent(){
        userRepository.updateStudent("themis13","12345","Themis13","Vasilakos13","themis13@email.com","themis");
        System.out.println(userRepository.findByUsername("themis13"));
    }
}