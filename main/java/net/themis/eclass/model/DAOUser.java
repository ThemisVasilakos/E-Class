package net.themis.eclass.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name ="user",uniqueConstraints = {@UniqueConstraint( name = "emailid_unique", columnNames = "email"),
        @UniqueConstraint( name = "username_unique", columnNames = "username")})
public class DAOUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long userId;
    @Column
    private String username;
    @Column
    @JsonIgnore
    private String password;
    @Column
    private String role;
    @Column
    private String firstName;
    @Column
    private String lastName;
    @Column
    private String email;


}
