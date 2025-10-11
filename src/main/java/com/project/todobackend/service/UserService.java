package com.project.todobackend.service;

import com.project.todobackend.dto.UserDTO;
import com.project.todobackend.entity.User;
import com.project.todobackend.exception.UsernameNotFoundException;
import com.project.todobackend.mapper.UserMapper;
import com.project.todobackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public Boolean registerUser(UserDTO userDTO) {
        User user = UserMapper.toUser(userDTO);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return true;
    }

    @Override
    @Transactional
    public Boolean updateUsername(String oldUsername, String newUsername) {
        User user = userRepository.findByUsername(oldUsername)
                .orElseThrow(() -> new UsernameNotFoundException(oldUsername));
        user.setUsername(newUsername);
        return true;
    }

    @Override
    @Transactional
    public Boolean updatePassword(String username, String newPassword) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
        user.setPassword(passwordEncoder.encode(newPassword));
        return true;
    }
}
