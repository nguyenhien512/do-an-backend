package com.hust.exam.mapper;

import com.hust.exam.DTO.UserDto;
import com.hust.exam.models.SystemUser;

import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {
    public static UserDto toDto(SystemUser user) {
        UserDto result = new UserDto();
        result.setUsername(user.getUsername());
        result.setFirstName(user.getFirstName());
        result.setLastName(user.getLastName());
        result.setAuthority(user.getAuthority());
        result.setActive(user.isActive());
        result.setDob(user.getDob());
        return result;
    }

    public static SystemUser toEntity(UserDto userDto) {
        SystemUser result = new SystemUser();
        result.setUsername(userDto.getUsername());
        result.setPassword(userDto.getPassword());
        result.setFirstName(userDto.getFirstName());
        result.setLastName(userDto.getLastName());
        result.setAuthority(userDto.getAuthority());
        return result;
    }

    public static List<UserDto> toDtoList(List<SystemUser> entities) {
        return entities.stream().map(UserMapper::toDto).collect(Collectors.toList());
    }

    public static List<SystemUser> toEntityList(List<UserDto> entities) {
        return entities.stream().map(UserMapper::toEntity).collect(Collectors.toList());
    }
}
