package com.hust.exam.controllers;

import com.hust.exam.DTO.StatisticRecord;
import com.hust.exam.DTO.UserDto;
import com.hust.exam.service.StatisticService;
import com.hust.exam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    public ResponseEntity<UserDto> registerUser(@RequestBody UserDto userDTO) {
        System.out.println("User DTO "+userDTO);
        return new ResponseEntity<>(userService.createUser(userDTO), HttpStatus.OK);
    }

    @GetMapping("")
    public List<UserDto> getAllUser() {
        return userService.findAll();
    }

    @GetMapping("/getCurrentUser")
    public ResponseEntity<UserDto> getByUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = null;
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            currentUserName = authentication.getName();
        }
        return new ResponseEntity<>(userService.findByUsername(currentUserName), HttpStatus.OK);
    }

    @PutMapping("")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = null;
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            currentUserName = authentication.getName();
        }
        return new ResponseEntity<>(userService.updateUser(currentUserName, userDTO), HttpStatus.OK);
    }

    @PostMapping("/inactive")
    public ResponseEntity<?> inactive(@RequestBody List<String> listUsername) {
        userService.inactive(listUsername);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/active")
    public ResponseEntity<?> active(@RequestBody List<String> listUsername) {
        userService.active(listUsername);
        return new ResponseEntity<>(HttpStatus.OK);
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

    @GetMapping("/getBySearch")
    @PreAuthorize("permitAll()")
    public List<UserDto> searchUsersByNameOrUsername(@RequestParam String queryString, @RequestParam List<String> authorities) {
        return userService.searchByNameOrUsername(queryString, authorities);
    }
}
