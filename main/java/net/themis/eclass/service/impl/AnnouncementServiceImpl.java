package net.themis.eclass.service.impl;

import net.themis.eclass.dto.AnnouncementDTO;
import net.themis.eclass.model.Announcement;
import net.themis.eclass.model.Course;
import net.themis.eclass.repository.AnnouncementRepository;
import net.themis.eclass.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AnnouncementServiceImpl {

    @Autowired
    private AnnouncementRepository announcementRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private EmailSenderService emailSenderService;

    public AnnouncementDTO createAnnouncement(AnnouncementDTO announcementDTO,Long courseId){
        Announcement announcement = new Announcement();

        Course course = courseRepository.findById(courseId).get();

        String title = course.getCourseName()+": "+ announcementDTO.getAnnouncementTitle();
        String body = announcementDTO.getAnnouncementBody();
        announcement.setAnnouncementTitle(title);
        announcement.setAnnouncementBody(body);
        announcementRepository.save(announcement);

        course.addAnnouncement(announcement);
        courseRepository.save(course);

        List<String> emails = announcementRepository.getEmails(courseId);
        for(int i=0;i<emails.size();i++){
            String email=emails.get(i);
            emailSenderService.sendEmail(email,title,body);
        }

        return announcementDTO;
    }

    public List<AnnouncementDTO> getCourseAnnouncements(Long courseId){
        List<AnnouncementDTO> announcementDTOS = new ArrayList<>();
        List<Announcement> announcements = announcementRepository.getAnnouncementByCourseId(courseId);

        for(int i=0;i<announcements.size();i++){
            AnnouncementDTO tmp = new AnnouncementDTO(announcements.get(i));
            announcementDTOS.add(tmp);
        }

        return announcementDTOS;
    }

    public List<AnnouncementDTO> getMyCoursesAnnouncements(){
        UserDetails userDetailService = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetailService.getUsername();

        List<AnnouncementDTO> announcementDTOS = new ArrayList<>();
        List<Announcement> announcements = announcementRepository.getMyCoursesAnnouncements(username);

        for(int i=0;i<announcements.size();i++){
            AnnouncementDTO tmp = new AnnouncementDTO(announcements.get(i));
            announcementDTOS.add(tmp);
        }

        return announcementDTOS;
    }
}
