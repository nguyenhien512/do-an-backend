package com.hust.exam.controllers;

import com.hust.exam.DTO.StudentExamDto;
import com.hust.exam.mapper.ExamMapper;
import com.hust.exam.service.ExamService;
import com.hust.exam.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@CrossOrigin
public class StudentController {

        @Autowired
        StudentService studentService;

        @Autowired
        ExamService examService;

        @GetMapping("/exams")
        public List<StudentExamDto> getExamsForStudent() {
                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                String currentUserName = null;
                if (!(authentication instanceof AnonymousAuthenticationToken)) {
                        currentUserName = authentication.getName();
                }
                return ExamMapper.toStudentExamDtoList(examService.findByStudent(currentUserName));
        }

}
