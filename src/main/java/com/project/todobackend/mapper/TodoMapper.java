package com.project.todobackend.mapper;

import com.project.todobackend.dto.TodoDTO;
import com.project.todobackend.entity.Todo;

public class TodoMapper {
    public static TodoDTO toTodoDTO(Todo todo) {
        return TodoDTO.builder()
                .id(todo.getId())
                .text(todo.getText())
                .completed(todo.isCompleted())
                .priorityTag(todo.getPriorityTag())
                .userId(todo.getUser().getId())
                .build();
    }

    public static Todo toTodoEntity(TodoDTO todoDTO) {
        return Todo.builder()
                .text(todoDTO.getText())
                .priorityTag(todoDTO.getPriorityTag())
                .completed(todoDTO.isCompleted())
                .build();
    }
}
