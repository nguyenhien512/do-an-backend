package com.hust.exam.controllers;

import com.hust.exam.DTO.ExamDto;
import com.hust.exam.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/exams")
@CrossOrigin
public class ExamController {

    @Autowired
    ExamService examService;

    @GetMapping("/publishedExam/forStudent")
    @PreAuthorize("hasAuthority('STUDENT')")
    public List<ExamDto> getPublishedExamForStudent() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = null;
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            currentUserName = authentication.getName();
        }
        return examService.findPublishedExamsByStudent(currentUserName);
    }

    @GetMapping("/forTeacher")
    @PreAuthorize("hasAuthority('TEACHER')")
    public List<ExamDto> getExamsOfTeacher() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = null;
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            currentUserName = authentication.getName();
        }
        return examService.findByTeacher(currentUserName);
    }

}
