package com.project.todobackend.controller;

import com.project.todobackend.dto.TodoDTO;
import com.project.todobackend.service.ITodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
@RequiredArgsConstructor
public class TodoController {

    private final ITodoService todoService;

    @GetMapping
    public ResponseEntity<List<TodoDTO>> getAllTodos(Authentication authentication) {
        List<TodoDTO> todos = todoService.getAllTodos(authentication.getName());
        return ResponseEntity.ok(todos);
    }

    @PostMapping
    public ResponseEntity<TodoDTO> addTodo(
            @RequestBody TodoDTO todoDTO,
            Authentication authentication
    ) {
        TodoDTO todo = todoService.addTodo(todoDTO, authentication.getName());
        return ResponseEntity.ok(todo);
    }

    @PutMapping("/{id}/toggle")
    public ResponseEntity<TodoDTO> toggleTodo(
            @PathVariable Long id,
            Authentication authentication
    ) {
        TodoDTO todo = todoService.toggleTodo(id, authentication.getName());
        return ResponseEntity.ok(todo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TodoDTO> updateTodo(
            @PathVariable Long id,
            @RequestBody TodoDTO todoDTO,
            Authentication authentication
    ) {
        TodoDTO todo = todoService.updateTodo(id, todoDTO, authentication.getName());
        return ResponseEntity.ok(todo);
    }

    @DeleteMapping
    public ResponseEntity<Boolean> deleteTodo(
            @PathVariable Long id,
            Authentication authentication
    ) {
        Boolean status = todoService.deleteTodo(id, authentication.getName());
        return ResponseEntity.ok(status);
    }
}
