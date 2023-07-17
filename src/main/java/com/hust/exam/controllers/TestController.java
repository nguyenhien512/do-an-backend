package com.hust.exam.controllers;

import com.hust.exam.DTO.StudentTestDto;
import com.hust.exam.DTO.TestResultDto;
import com.hust.exam.mapper.TestMapper;
import com.hust.exam.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/tests")
@CrossOrigin
public class TestController {

    @Autowired
    TestService testService;

    @PostMapping("")
    @PreAuthorize("hasAuthority('STUDENT')")
    public StudentTestDto createTest(@RequestParam int examId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = null;
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            currentUserName = authentication.getName();
        }
        return TestMapper.toExamTestDto(testService.createTest(currentUserName, examId));
    }

    @PostMapping ("/{testId}/answers")
    @PreAuthorize("hasAuthority('STUDENT')")
    public void postAnswers(@PathVariable int testId, @RequestBody Map<Integer, String> answers) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = null;
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            currentUserName = authentication.getName();
        }
        testService.postAnswers(currentUserName,testId, answers);
    }

    @GetMapping("/{testId}/result")
    @PreAuthorize("hasAuthority('STUDENT')")
    public TestResultDto getResult(@PathVariable int testId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = null;
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            currentUserName = authentication.getName();
        }
        return TestMapper.toTestResultDto(testService.getResult(currentUserName,testId));
    }
}
