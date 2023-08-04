package com.hust.exam.service;

import com.hust.exam.DTO.UserDto;
import com.hust.exam.models.SystemUser;

import java.util.List;

public interface UserService {
    List<UserDto> findAll();
    SystemUser findWithUsername(String username);
    UserDto findByUsername(String username);
    int getDataNumber();

    UserDto createUser(UserDto userDTO);

    UserDto updateUser(String requestUsername, UserDto userDto);

    void inactive(List<String> listUsername);

    void active(List<String> listUsername);

    public List<UserDto> searchByNameOrUsername(String queryString, List<String> authorities);
}
