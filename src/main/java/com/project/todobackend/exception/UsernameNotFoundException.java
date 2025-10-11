package com.project.todobackend.exception;

public class UsernameNotFoundException extends RuntimeException {
    public UsernameNotFoundException(String username) {
        super("User not found with username: " + username);
    }
}
