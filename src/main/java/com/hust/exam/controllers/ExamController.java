package com.hust.exam.controllers;

import com.hust.exam.DTO.ExamDto;
import com.hust.exam.mapper.ExamMapper;
import com.hust.exam.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExamController {

    @Autowired
    ExamService examService;

    @GetMapping("/api/exams/{examId}")
    @PreAuthorize("hasAuthority('STUDENT')")
    public ExamDto getExam(@PathVariable int examId) {
        return ExamMapper.toExamDto(examService.findById(examId));
    }
}
