package net.themis.eclass.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "course",uniqueConstraints = @UniqueConstraint( name = "coursename_unique", columnNames = "courseName"))
public class Course {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long courseId;

    private String courseName;
    private String courseDesc;

    @ManyToMany( cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JsonIgnore
    @JoinTable(
            joinColumns = @JoinColumn(
                    name = "course_id",
                    referencedColumnName = "courseId"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "user_id",
                    referencedColumnName = "userId"
            )
    )
    private List<DAOUser> users;

    public void addStudents(DAOUser user){
        if(users == null) users = new ArrayList<>();
        users.add(user);
    }

    public void removeStudent(String username){
        if(users == null) {
            return;
        }
        else{
            for(int i=0;i<users.size();i++){
                if(users.get(i).getUsername().equals(username)){
                    users.remove(i);
                    return;
                }
            }
        }
    }

}
