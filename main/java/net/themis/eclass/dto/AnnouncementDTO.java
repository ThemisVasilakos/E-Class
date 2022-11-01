package net.themis.eclass.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.themis.eclass.model.Announcement;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnnouncementDTO {

    private String announcementTitle;
    private String announcementBody;

    public AnnouncementDTO(Announcement announcement){
        this.announcementTitle=announcement.getAnnouncementTitle();
        this.announcementBody=announcement.getAnnouncementBody();
    }
}
