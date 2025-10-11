package com.project.todobackend.controller;

import com.project.todobackend.dto.UserDTO;
import com.project.todobackend.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/public")
@RequiredArgsConstructor
public class PublicController {

    private final IUserService userService;

    @GetMapping("/ping")
    public String ping() {
        return "pong";
    }

    @PostMapping("/register")
    public ResponseEntity<Boolean> registerUser(@RequestBody UserDTO userDTO) {
        Boolean userDTOResponseStatus = userService.registerUser(userDTO);
        return ResponseEntity.ok(userDTOResponseStatus);
    }

}