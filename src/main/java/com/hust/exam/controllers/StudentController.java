package com.hust.exam.controllers;

import com.hust.exam.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/students")
public class StudentController {

        @Autowired
        StudentService studentService;


//        @GetMapping("/{username}")
//        public StudentDto getStudent(@PathVariable String username) {
//               Student student = studentService.getStudent(username);
//
//        }

}
