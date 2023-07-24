package com.hust.exam.controllers;

import com.hust.exam.DTO.StudentDto;
import com.hust.exam.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@CrossOrigin
public class StudentController {

    @Autowired
    StudentService studentService;

    @GetMapping("")
    @PreAuthorize("hasAuthority('TEACHER')")
    public List<StudentDto> searchStudentsByNameOrUsername(@RequestParam String queryString) {
        return studentService.searchByNameOrUsername(queryString);
    }
}
