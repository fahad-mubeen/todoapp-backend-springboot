package com.project.todobackend.service;

import com.project.todobackend.dto.UserDTO;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    @Override
    public Boolean registerUser(UserDTO userDTO) {
        return null;
    }

    @Override
    public Boolean updateUsername(String oldUsername, String newUsername) {
        return null;
    }

    @Override
    public Boolean updatePassword(String username, String newPassword) {
        return null;
    }
}
