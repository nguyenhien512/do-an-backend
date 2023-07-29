package com.hust.exam.controllers;

import com.hust.exam.DTO.TestDto;
import com.hust.exam.DTO.TestResultDto;
import com.hust.exam.DTO.TestSubmitDto;
import com.hust.exam.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tests")
@CrossOrigin
public class TestController {

    @Autowired
    TestService testService;

    @PostMapping("")
    @PreAuthorize("hasAuthority('STUDENT')")
    public TestDto createTest(@RequestParam int examId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = null;
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            currentUserName = authentication.getName();
        }
        return testService.createTest(currentUserName, examId);
    }

    @PostMapping ("/{testId}/answers")
    @PreAuthorize("hasAuthority('STUDENT')")
    public void postAnswers(@PathVariable int testId, @RequestBody TestSubmitDto testSubmitDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = null;
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            currentUserName = authentication.getName();
        }
        testService.postAnswers(currentUserName,testId, testSubmitDto);
    }

    @GetMapping("/{testId}/result/forStudent")
    @PreAuthorize("hasAuthority('STUDENT')")
    public TestResultDto getResult(@PathVariable int testId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = null;
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            currentUserName = authentication.getName();
        }
        return testService.getResult(currentUserName,testId);
    }

    @GetMapping("/results/forStudent")
    @PreAuthorize("hasAuthority('STUDENT')")
    public List<TestResultDto> getResults() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = null;
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            currentUserName = authentication.getName();
        }
        return testService.getResults(currentUserName);
    }

    @GetMapping("")
    @PreAuthorize("hasAuthority('TEACHER')")
    public List<TestResultDto> getTestsByExam(@RequestParam int examId) {
        return testService.getResultByExam(examId);
    }

    @GetMapping("/{testId}/result-detail")
    @PreAuthorize("permitAll()")
    public TestDto getTestDetail(@PathVariable int testId) {
        return testService.getDetail(testId);
    }
}
