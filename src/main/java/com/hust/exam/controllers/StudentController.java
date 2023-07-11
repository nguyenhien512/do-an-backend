package com.hust.exam.controllers;

import com.hust.exam.DTO.QuestionDTO;
import com.hust.exam.DTO.StudentDTO;
import com.hust.exam.mapper.StudentMapper;
import com.hust.exam.models.Student;
import com.hust.exam.service.QuestionService;
import com.hust.exam.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/students")
public class StudentController {

        @Autowired
        StudentService studentService;

        @GetMapping("/{username}")
        public StudentDTO getAll(@PathVariable String username) {
                return StudentMapper.studentToStudentDTO(studentService.getStudent(username));
        }

}
