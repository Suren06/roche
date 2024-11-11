package com.roche.service;

import com.roche.dto.UserDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    UserDto createUser(UserDto userDto);

    UserDto getUserByEmail(String email);

    Page<UserDto> getAllUsers(Pageable pageable);
}
