package com.project.todobackend.exception;

public class TodoAccessDeniedException extends RuntimeException {
    public TodoAccessDeniedException(Long id) {
        super("Access denied to todo with id: " + id);
    }
}
