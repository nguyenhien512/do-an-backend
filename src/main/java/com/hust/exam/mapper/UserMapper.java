package com.hust.exam.mapper;

import com.hust.exam.DTO.UserDTO;
import com.hust.exam.models.SystemUser;

import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {
    public static UserDTO toDto(SystemUser user) {
        UserDTO result = new UserDTO();
        result.setUsername(user.getUsername());
        result.setPassword(user.getPassword());
        result.setFirstName(user.getFirstName());
        result.setLastName(user.getLastName());
        return result;
    }

    public static SystemUser toEntity(UserDTO userDto) {
        SystemUser result = new SystemUser();
        result.setUsername(userDto.getUsername());
        result.setPassword(userDto.getPassword());
        result.setFirstName(userDto.getFirstName());
        result.setLastName(userDto.getLastName());
        return result;
    }

    public static List<UserDTO> toDtoList(List<SystemUser> entities) {
        return entities.stream().map(UserMapper::toDto).collect(Collectors.toList());
    }

    public static List<SystemUser> toEntityList(List<UserDTO> entities) {
        return entities.stream().map(UserMapper::toEntity).collect(Collectors.toList());
    }
}
