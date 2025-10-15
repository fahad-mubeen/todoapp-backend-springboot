package com.project.todobackend.controller;

import com.project.todobackend.dto.UserDTO;
import com.project.todobackend.entity.Admin;
import com.project.todobackend.entity.User;
import com.project.todobackend.enums.UserRole;
import com.project.todobackend.repository.AdminRepository;
import com.project.todobackend.repository.UserRepository;
import com.project.todobackend.service.IUserService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/public")
@RequiredArgsConstructor
public class PublicController {

    private final IUserService userService;

    private final AdminRepository adminRepository;

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Value("${admin.username}")
    private String adminUsername;

    @Value("${admin.password}")
    private String adminPassword;

    @GetMapping("/ping")
    public String ping() {
        return "pong";
    }

    @PostMapping("/register")
    public ResponseEntity<Boolean> registerUser(@RequestBody UserDTO userDTO) {
        Boolean userDTOResponseStatus = userService.registerUser(userDTO);
        return ResponseEntity.ok(userDTOResponseStatus);
    }

    @PostMapping("/create-admin")
    @Transactional
    public ResponseEntity<?> createAdmin() {
        boolean adminExists = adminRepository.count() > 0;
        if (adminExists) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Admin user already exists.");
        }

        User adminUser = User.builder()
                .username(adminUsername)
                .password(passwordEncoder.encode(adminPassword))
                .name("Admin")
                .userRole(UserRole.ADMIN)
                .build();

        adminRepository.save(new Admin());
        userRepository.save(adminUser);

        return ResponseEntity.ok("Admin user created successfully.");
    }

    @GetMapping("/login")
    public String login(UserDTO userDTO) {
        return userService.login(userDTO);
    }
}