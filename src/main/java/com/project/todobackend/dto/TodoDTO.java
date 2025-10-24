package com.project.todobackend.dto;

import com.project.todobackend.enums.PriorityTag;
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
    private PriorityTag priorityTag = PriorityTag.MEDIUM;
    private Long userId;
}
