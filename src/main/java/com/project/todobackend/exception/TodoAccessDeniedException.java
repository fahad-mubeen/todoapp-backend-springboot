package com.project.todobackend.exception;

public class TodoAccessDeniedException extends RuntimeException {
    public TodoAccessDeniedException(String message) {
        super(message);
    }
}
