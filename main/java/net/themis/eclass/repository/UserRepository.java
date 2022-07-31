package net.themis.eclass.repository;

import net.themis.eclass.model.DAOUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<DAOUser, Long> {
    DAOUser findByUsername(String username);
    List<DAOUser> findByRole(String username);

    @Query(value="SELECT u.*\n" +
            "FROM user u\n" +
            "inner JOIN course_users cu ON u.user_id = cu.user_id\n" +
            "inner JOIN course c ON c.course_id = cu.course_id\n" +
            "where course_name=?1",nativeQuery = true)
    List<DAOUser> getStudentsFromCourse(String courseName);

    @Modifying
    @Transactional
    @Query(value="update user set username=?1,password=?2,first_name=?3,last_name=?4,email=?5 where username=?6",nativeQuery = true)
    int updateStudent(String username,String password,String firstName,String lastName,String email,String loggedUser);
}