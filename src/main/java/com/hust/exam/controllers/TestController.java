package com.hust.exam.controllers;

import com.hust.exam.DTO.ExamTestDto;
import com.hust.exam.mapper.TestMapper;
import com.hust.exam.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tests")
public class TestController {

    @Autowired
    TestService testService;

    @PostMapping("")
    @PreAuthorize("hasAuthority('STUDENT')")
    public ExamTestDto createTest(@RequestParam int examId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = null;
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            currentUserName = authentication.getName();
        }
        return TestMapper.toExamTestDto(testService.createTest(currentUserName, examId));
    }

    @PostMapping ("/postAnswers")
    @PreAuthorize("hasAuthority('STUDENT')")
    public void postAnswers(@RequestBody ExamTestDto testWithAnswers) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = null;
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            currentUserName = authentication.getName();
        }
        testService.postAnswers(currentUserName, testWithAnswers);
    }

    @GetMapping("/testResult")
    @PreAuthorize("hasAuthority('STUDENT')")
    public ExamTestDto getResult(@RequestParam int testId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = null;
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            currentUserName = authentication.getName();
        }
        return null;
    }
}
