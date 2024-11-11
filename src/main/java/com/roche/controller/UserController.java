package com.roche.controller;

import com.roche.dto.PaginatedResponse;
import com.roche.dto.UserDto;
import com.roche.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "user", produces = "application/json")
@Slf4j
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private final PagedResourcesAssembler<UserDto> pagedResourcesAssembler;

    @PostMapping
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
        log.info("Received request to create a user: {}", userDto);
        UserDto newUser = userService.createUser(userDto);
        return ResponseEntity.status(201).body(newUser);
    }

    @GetMapping("/{email}")
    public ResponseEntity<UserDto> getUserByEmail(@PathVariable String email) {
        log.info("Received request to find user by email");
        UserDto userDto = userService.getUserByEmail(email);
        return ResponseEntity.ok(userDto);  // Return 200 OK
    }

    @GetMapping
    public ResponseEntity<PaginatedResponse<UserDto>> getAllUsers(
            @PageableDefault(size = 10, sort = "name") Pageable pageable) {
        log.info("Received request to fetch all users with pagination");
        Page<UserDto> userPage = userService.getAllUsers(pageable);
        PaginatedResponse<UserDto> response = new PaginatedResponse<>(userPage);
        return ResponseEntity.ok(response);
    }
}
