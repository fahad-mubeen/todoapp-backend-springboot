package com.project.todobackend.service;

import com.project.todobackend.dto.TodoDTO;

import java.util.List;

public interface ITodoService {
    List<TodoDTO> getAllTodos(String username);

    TodoDTO addTodo(TodoDTO todoDTO, String username);

    TodoDTO toggleTodo(Long todoId, String username);

    TodoDTO updateTodo(Long todoId, TodoDTO todoDTO, String username);

    Boolean deleteTodo(Long todoId, String username);
}
