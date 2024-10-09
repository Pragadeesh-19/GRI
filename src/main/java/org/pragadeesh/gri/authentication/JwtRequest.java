package org.pragadeesh.gri.authentication;

import lombok.Data;
import org.pragadeesh.gri.entity.Role;

@Data
public class JwtRequest {

    private String username;
    private String password;
    private String location;
    private String shift;
    private Role role;
}
