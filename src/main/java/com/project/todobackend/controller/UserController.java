package com.project.todobackend.controller;

import com.project.todobackend.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final IUserService userService;

    @PutMapping("/updateUsername")
    public ResponseEntity<Boolean> updateUsername(
            Authentication authentication,
            @RequestBody String newUsername
    ) {
        String oldUsername = authentication.getName();
        Boolean response = userService.updateUsername(oldUsername, newUsername);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/updatePassword")
    public ResponseEntity<Boolean> updatePassword(
            Authentication authentication,
            String newPassword
    ) {
        String username = authentication.getName();
        Boolean response = userService.updatePassword(username, newPassword);
        return ResponseEntity.ok(response);
    }
}
