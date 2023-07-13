package com.hust.exam.service;

import com.hust.exam.DTO.UserDTO;
import com.hust.exam.models.SystemUser;

import java.util.List;

public interface UserService {
    List<UserDTO> findAll();
    SystemUser findWithUsername(String username);
    UserDTO findByUsername(String username);
    int getDataNumber();
}
