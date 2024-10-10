package org.pragadeesh.gri.dto;

import lombok.Data;
import org.pragadeesh.gri.entity.Role;

@Data
public class UserSignupDto {

    private String username;
    private String password;
    private Role role;
}
