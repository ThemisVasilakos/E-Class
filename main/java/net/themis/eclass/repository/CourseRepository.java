package net.themis.eclass.repository;

import net.themis.eclass.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {

    Course findByCourseName(String name);

    @Query(value = "SELECT c.*\n" +
            "FROM course c\n" +
            "inner JOIN course_users cu ON c.course_id = cu.course_id\n" +
            "inner JOIN user u ON u.user_id = cu.user_id\n" +
            "where username=?1" ,nativeQuery = true)
    List<Course> findMyCourses(String name);

}
