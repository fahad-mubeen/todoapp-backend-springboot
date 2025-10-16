package com.project.todobackend.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TodoDTO {
    private Long id;
    private String text;
    private boolean completed = false;
    private Long userId;
}
