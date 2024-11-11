package com.roche.mapper;

import com.roche.dto.UserDto;
import com.roche.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper {

    UserDto userToDto(User user);

    User dtoToUser(UserDto userDto);
}
