package com.project.todobackend.dto;

import com.project.todobackend.enums.UserRole;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {
    private Long id;
    private String username;
    private String password;
    private String name;
    private UserRole role = UserRole.USER;
    private List<TodoDTO> todos;
}
