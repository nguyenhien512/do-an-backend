package com.hust.exam.controllers;

import com.hust.exam.DTO.StatisticRecord;
import com.hust.exam.DTO.UserDto;
import com.hust.exam.mapper.UserMapper;
import com.hust.exam.service.StatisticService;
import com.hust.exam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")

public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    StatisticService statisticService;

    @PostMapping("")
    public UserDto registerUser(@RequestBody UserDto userDTO) {
        System.out.println("User DTO "+userDTO);
        return UserMapper.toDto(userService.createUser(userDTO));
    }

    @GetMapping("/statistic/byPoint/{id}")
    public ResponseEntity<List<StatisticRecord>> getStatisticByExamId(@PathVariable("id") int id) {
        return new ResponseEntity<>(statisticService.getDataByExam(id), HttpStatus.OK);
    }

    @GetMapping("/statistic/byQues/{id}")
    public ResponseEntity<List<StatisticRecord>> getStatisticByQuesId(@PathVariable("id") int id) {
        return new ResponseEntity<>(statisticService.getDataByQuesAnswer(id), HttpStatus.OK);
    }

    @GetMapping("/statistic/byStudent/{id}")
    public ResponseEntity<List<StatisticRecord>> getStatisticByStudentId(@PathVariable("id") int id) {
        return new ResponseEntity<>(statisticService.getStudentInfor(id), HttpStatus.OK);
    }

    @GetMapping("/statistic/average/{id}")
    public ResponseEntity<Float> getAverageByExamId(@PathVariable("id") int id) {
        return new ResponseEntity<>(statisticService.getAverageScore(id), HttpStatus.OK);
    }
}
