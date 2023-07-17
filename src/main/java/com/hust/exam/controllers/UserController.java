package com.hust.exam.controllers;

import com.hust.exam.DTO.UserDto;
import com.hust.exam.mapper.UserMapper;
import com.hust.exam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@CrossOrigin
public class UserController {

    @Autowired
    UserService userService;
    @PostMapping("")
    public UserDto registerUser(@RequestBody UserDto userDTO) {
        return UserMapper.toDto(userService.createUser(userDTO));
    }
}
