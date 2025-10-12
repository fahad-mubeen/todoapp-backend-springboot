package com.project.todobackend.repository;

import com.project.todobackend.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    long count();
}
