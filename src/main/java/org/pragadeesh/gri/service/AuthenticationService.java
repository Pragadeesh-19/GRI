package org.pragadeesh.gri.service;

import org.pragadeesh.gri.entity.JwtResponse;
import org.pragadeesh.gri.entity.Manager;
import org.pragadeesh.gri.repository.ManagerRepository;
import org.pragadeesh.gri.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final ManagerRepository managerRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthenticationService(ManagerRepository managerRepository, AuthenticationManager authenticationManager, JwtUtil jwtUtil, PasswordEncoder passwordEncoder) {
        this.managerRepository = managerRepository;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }

    public String register(Manager request) {
        Manager manager = new Manager();
        manager.setUsername(request.getUsername());
        manager.setPassword(passwordEncoder.encode(request.getPassword()));
        manager.setRole(request.getRole());

        managerRepository.save(manager);
        return "Manager Registered Successfully";
    }

    public JwtResponse login(Manager request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        Manager manager = managerRepository.findByUsername(request.getUsername()).orElseThrow();
        String token = jwtUtil.generateToken(manager);

        return new JwtResponse(token);
    }
}
