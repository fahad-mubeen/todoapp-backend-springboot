package com.project.todobackend.mapper;

import com.project.todobackend.dto.UserDTO;
import com.project.todobackend.entity.User;
import com.project.todobackend.enums.UserRole;

import java.util.stream.Collectors;

public class UserMapper {
    public static UserDTO toUserDTO(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password("")
                .name(user.getName())
                .role(user.getUserRole())
                .todos(user.getTodos().stream().map(TodoMapper::toTodoDTO).collect(Collectors.toList()))
                .build();
    }

    public  static User toUser(UserDTO userDTO) {
        return User.builder()
                .username(userDTO.getUsername())
                .password(userDTO.getPassword())
                .name(userDTO.getName())
                .userRole(UserRole.USER)
                .build();
    }
}
