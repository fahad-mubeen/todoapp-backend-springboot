package com.project.todobackend.entity;

import com.project.todobackend.enums.UserRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "users")
public class User extends BaseEntity {
    @Column(nullable = false, unique = true)
    @NotBlank(message = "Username is required")
    private String username;

    @Column(nullable = false)
    @NotBlank(message = "Password is required")
    private String password;

    @Column(nullable = false)
    @NotBlank(message = "name is required")
    private String name;

    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    @OneToMany(
            mappedBy = "user"
            ,orphanRemoval = true
            ,cascade = CascadeType.ALL
            ,fetch = FetchType.LAZY
    )
    private List<Todo> todos;
}
