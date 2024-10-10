package org.pragadeesh.gri.authentication;

import org.pragadeesh.gri.dto.UserLoginDto;
import org.pragadeesh.gri.dto.UserSignupDto;
import org.pragadeesh.gri.entity.User;
import org.pragadeesh.gri.service.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody UserSignupDto userSignupDto) {
        return ResponseEntity.ok(authenticationService.register(userSignupDto));
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody UserLoginDto userLoginDto) {
        return ResponseEntity.ok(authenticationService.login(userLoginDto));
    }
}
