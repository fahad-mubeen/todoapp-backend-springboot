package com.project.todobackend.service;

import com.project.todobackend.dto.TodoDTO;

import java.util.List;

public interface ITodoService {
    List<TodoDTO> getAllTodos(String username);

    TodoDTO addTodo(TodoDTO todoDTO, String username);

    TodoDTO toggleTodo(Long id, String username);

    TodoDTO updateTodo(Long id, TodoDTO todoDTO, String username);

    Boolean deleteTodo(Long id, String username);
}
