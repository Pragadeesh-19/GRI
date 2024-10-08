package org.pragadeesh.gri.entity;

import lombok.Data;

@Data
public class JwtRequest {

    private String username;
    private String password;
    private Role role;
}
