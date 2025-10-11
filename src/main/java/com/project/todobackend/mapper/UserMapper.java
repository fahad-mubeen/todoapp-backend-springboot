package com.project.todobackend.mapper;

import com.project.todobackend.dto.UserDTO;
import com.project.todobackend.entity.User;

public class UserMapper {
    public static UserDTO toUserDTO(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .name(user.getName())
                .build();
    }
}
