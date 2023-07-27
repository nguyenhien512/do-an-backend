package com.hust.exam.controllers;

import com.hust.exam.DTO.ExamCountDto;
import com.hust.exam.DTO.ExamDto;
import com.hust.exam.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/submittedExam/count")
    public List<ExamCountDto> countSubmitExams(@RequestParam int classId) {
        return examService.countTestsByClassGroupByStudent(classId);
    }

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('TEACHER')")
    public ResponseEntity<List<ExamDto>> getAllExam() {
        return new ResponseEntity<>(examService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('TEACHER')")
    public ResponseEntity<ExamDto> getExamById(@PathVariable("id") int id) {
        return new ResponseEntity<>(examService.findExamById(id), HttpStatus.OK);
    }

    @PutMapping("/config")
    @PreAuthorize("hasAuthority('TEACHER')")
    public ResponseEntity<ExamDto> updateExam(@RequestBody ExamDto dto) {
        return new ResponseEntity<>(examService.updateExam(dto), HttpStatus.OK);
    }

    @PutMapping("/publish/{id}")
    @PreAuthorize("hasAuthority('TEACHER')")
    public ResponseEntity<ExamDto> publishExam(@PathVariable("id") int id) {
        return new ResponseEntity<>(examService.publishExam(id), HttpStatus.OK);
    }

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('TEACHER')")
    public ResponseEntity<ExamDto> createExam(@RequestBody ExamDto dto) {
        return new ResponseEntity<>(examService.createExam(dto), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('TEACHER')")
    public ResponseEntity<?> deleteExam(@PathVariable("id") int id) {
        examService.deleteExam(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
