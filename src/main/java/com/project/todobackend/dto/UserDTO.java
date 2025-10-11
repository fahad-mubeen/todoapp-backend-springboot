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
    private String name;
    private UserRole role;
    private List<TodoDTO> todos;
}
