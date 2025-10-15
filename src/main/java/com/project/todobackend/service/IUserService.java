package com.project.todobackend.service;

import com.project.todobackend.dto.UserDTO;

import java.util.List;

public interface IUserService {

    Boolean registerUser(UserDTO userDTO);

    Boolean updateUsername(String oldUsername, String newUsername);

    Boolean updatePassword(String username, String newPassword);

    Boolean deleteUser(String username);

    List<UserDTO> listAllUsers();

    String login(UserDTO userDTO);
}
