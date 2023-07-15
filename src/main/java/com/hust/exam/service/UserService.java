package com.hust.exam.service;

import com.hust.exam.DTO.UserDto;
import com.hust.exam.models.SystemUser;

import java.util.List;

public interface UserService {
    List<UserDto> findAll();
    SystemUser findWithUsername(String username);
    UserDto findByUsername(String username);
    int getDataNumber();

    SystemUser createUser(UserDto userDTO);
}
