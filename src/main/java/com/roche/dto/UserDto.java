package com.roche.dto;

import lombok.Builder;

import java.time.LocalDateTime;

public record UserDto(
        Long id,
        String name,
        String email,
        LocalDateTime dob,
        LocalDateTime createdDate,
        LocalDateTime updatedDate,
        Boolean isActive
) {
    @Builder
    public UserDto {
        // Optional: Add validation or additional processing logic here if required.
    }
}