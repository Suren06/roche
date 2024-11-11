package com.roche.serviceImpl;

import com.roche.dto.UserDto;
import com.roche.exceptions.UserNotFoundException;
import com.roche.mapper.UserMapper;
import com.roche.model.User;
import com.roche.repository.UserRepository;
import com.roche.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    @Transactional
    public UserDto createUser(UserDto userDto) {
        User user = userMapper.dtoToUser(userDto);
        User savedUser = userRepository.save(user);
        log.info("User created successfully with ID: {}", savedUser.getId());
        return userMapper.userToDto(savedUser);
    }

    @Transactional(readOnly = true)
    public UserDto getUserByEmail(String email) {
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Email must not be null or empty");
        }
        return userRepository.findByEmail(email)
                .map(userMapper::userToDto)
                .orElseThrow(() -> new UserNotFoundException("User not found with email: " + email));
    }

    @Transactional(readOnly = true)
    public Page<UserDto> getAllUsers(Pageable pageable) {
        log.info("Fetching all users");
        Page<User> userPage = userRepository.findAll(pageable);
        return userPage.map(userMapper::userToDto);
    }
}
