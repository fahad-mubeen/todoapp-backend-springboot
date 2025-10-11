package com.project.todobackend.service;


import com.project.todobackend.dto.TodoDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService implements ITodoService {

    @Override
    public List<TodoDTO> getAllTodos(String username) {
        return List.of();
    }

    @Override
    public TodoDTO addTodo(TodoDTO todoDTO, String username) {
        return null;
    }

    @Override
    public TodoDTO toggleTodo(Long id, String username) {
        return null;
    }

    @Override
    public TodoDTO updateTodo(Long id, TodoDTO todoDTO, String username) {
        return null;
    }

    @Override
    public Boolean deleteTodo(Long id, String username) {
        return null;
    }
}
