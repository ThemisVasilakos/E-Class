package net.themis.eclass.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.themis.eclass.model.Announcement;
import net.themis.eclass.model.Course;
import net.themis.eclass.model.DAOUser;
import net.themis.eclass.model.File;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseDTO {

    private String courseName;
    private String courseDesc;

    @JsonIgnore
    private List<DAOUser> users;

    @JsonIgnore
    private List<Announcement> announcements;

    @JsonIgnore
    private List<File> files;

    public CourseDTO(Course course){
        this.courseName=course.getCourseName();
        this.courseDesc=course.getCourseDesc();
        this.users=course.getUsers();
        this.announcements=course.getAnnouncements();
        this.files=course.getFiles();
    }

}
