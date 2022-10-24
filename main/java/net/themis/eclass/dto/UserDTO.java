package net.themis.eclass.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class UserDTO {
    private String username;
    private String password;
    private String role;
    private String firstName;
    private String lastName;
    private String email;

}
