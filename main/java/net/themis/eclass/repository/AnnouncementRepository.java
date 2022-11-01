package net.themis.eclass.repository;

import net.themis.eclass.model.Announcement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AnnouncementRepository extends JpaRepository<Announcement,Long> {

    @Query(value = "SELECT a.*\n" +
            "            FROM announcement a\n" +
            "            inner JOIN course_announcements ca ON a.announcement_id = ca.announcement_id \n" +
            "            inner JOIN course c ON c.course_id = ca.course_id\n" +
            "            where c.course_id=?1",nativeQuery = true)
    List<Announcement> getAnnouncementByCourseId(Long courseId);

    @Query(value = "SELECT a.*\n" +
            "            FROM announcement a\n" +
            "            inner JOIN course_announcements ca ON a.announcement_id = ca.announcement_id \n" +
            "            inner JOIN course c ON c.course_id = ca.course_id\n" +
            "            inner JOIN course_users cu ON c.course_id = cu.course_id\n" +
            "            inner JOIN user u ON u.user_id = cu.user_id\n" +
            "            where u.username=?1",nativeQuery = true)
    List<Announcement> getMyCoursesAnnouncements(String username);

    @Query(value = "SELECT u.email\n" +
            "            FROM user u\n" +
            "            inner JOIN course_users cu ON u.user_id = cu.user_id\n" +
            "            inner JOIN course c ON c.course_id = cu.course_id\n" +
            "            where c.course_id=?1",nativeQuery = true)
    List<String> getEmails(Long courseId);
}
