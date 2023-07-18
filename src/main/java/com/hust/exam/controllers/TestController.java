package com.hust.exam.controllers;

import com.hust.exam.DTO.StudentTestDto;
import com.hust.exam.DTO.TeacherTestDto;
import com.hust.exam.DTO.TestResultDto;
import com.hust.exam.DTO.TestSubmitDto;
import com.hust.exam.mapper.TestMapper;
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
    public StudentTestDto createTest(@RequestParam int examId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = null;
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            currentUserName = authentication.getName();
        }
        return TestMapper.toStudentTestDto(testService.createTest(currentUserName, examId));
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
        return TestMapper.toTestResultDto(testService.getResult(currentUserName,testId));
    }

    @GetMapping("")
    @PreAuthorize("hasAuthority('TEACHER')")
    public List<TestResultDto> getTestsByExam(@RequestParam int examId) {
        return TestMapper.toTestResultDtoList(testService.getByExam(examId));
    }

    @GetMapping("/{testId}/result/forTeacher")
    @PreAuthorize("hasAuthority('TEACHER')")
    public TeacherTestDto getFullResult(@PathVariable int testId) {
        return TestMapper.toTeacherTestDto(testService.getResult(testId));
    }
}
