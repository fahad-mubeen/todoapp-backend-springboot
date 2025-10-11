package com.project.todobackend.service;


import com.project.todobackend.dto.TodoDTO;
import com.project.todobackend.entity.Todo;
import com.project.todobackend.entity.User;
import com.project.todobackend.exception.TodoAccessDeniedException;
import com.project.todobackend.exception.TodoNotFoundException;
import com.project.todobackend.exception.UsernameNotFoundException;
import com.project.todobackend.mapper.TodoMapper;
import com.project.todobackend.repository.TodoRepository;
import com.project.todobackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService implements ITodoService {

    private final TodoRepository todoRepository;

    private final UserRepository userRepository;

    @Override
    public List<TodoDTO> getAllTodos(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
        List<Todo> todoList = todoRepository.findByUser_Id(user.getId());
        return todoList.stream().map(TodoMapper::toTodoDTO).toList();
    }

    @Override
    public TodoDTO addTodo(TodoDTO todoDTO, String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
        Todo todo = TodoMapper.toTodoEntity(todoDTO);
        todo.setUser(user);
        Todo savedTodo = todoRepository.save(todo);
        return TodoMapper.toTodoDTO(savedTodo);
    }

    private Todo findAndValidateTodo(Long todoId, String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
        Todo todo = todoRepository.findById(todoId)
                .orElseThrow(() -> new TodoNotFoundException(todoId));
        if (!todo.getUser().getId().equals(user.getId())) {
            throw new TodoAccessDeniedException(todoId);
        }
        return todo;
    }

    @Override
    @Transactional
    public TodoDTO toggleTodo(Long todoId, String username) {
        Todo todo = findAndValidateTodo(todoId, username);
        todo.setCompleted(!todo.isCompleted());
        return TodoMapper.toTodoDTO(todo);
    }

    @Override
    @Transactional
    public TodoDTO updateTodo(Long todoId, TodoDTO todoDTO, String username) {
        Todo todo = findAndValidateTodo(todoId, username);
        todo.setText(todoDTO.getText());
        todo.setCompleted(todoDTO.isCompleted());
        return TodoMapper.toTodoDTO(todo);
    }

    @Override
    public Boolean deleteTodo(Long todoId, String username) {
        Todo todo = findAndValidateTodo(todoId, username);
        todoRepository.delete(todo);
        return true;
    }
}
