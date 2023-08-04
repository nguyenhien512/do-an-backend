package com.hust.exam.controllers;

import com.hust.exam.DTO.QuestionDto;
import com.hust.exam.enumobject.QuestionStatus;
import com.hust.exam.service.QuestionService;
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
@RequestMapping("/api/questions")
@PreAuthorize("hasAuthority('TEACHER') or hasAuthority('ADMIN')")
@CrossOrigin
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @GetMapping("")
    public List<QuestionDto> getAll() {
        return questionService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuestionDto> getById(@PathVariable("id") int id) {
        return new ResponseEntity<>(questionService.findById(id), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<QuestionDto> createQuestion(@RequestBody QuestionDto dto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = null;
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            currentUserName = authentication.getName();
        }
        System.out.println("Question dto : "+dto);
        return new ResponseEntity<>(questionService.createQuestion(currentUserName, dto), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<QuestionDto> editQuestion(@RequestBody QuestionDto dto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = null;
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            currentUserName = authentication.getName();
        }
        return new ResponseEntity<>(questionService.editQuestion(currentUserName, dto), HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteQuestion(@RequestBody List<Integer> qIds) {
        questionService.deleteQuestion(qIds);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/getByExam")
    public List<QuestionDto> getQuestionsByExam(@RequestParam int examId){
        return questionService.getQuestionsByExam(examId);
    }

    @GetMapping("/getBySearch")
    public List<QuestionDto> getQuestionsBySearchKey(@RequestParam String searchKey, @RequestParam List<QuestionStatus> status){
        return questionService.searchByContent(searchKey, status);
    }

    @PostMapping("/approve")
    public ResponseEntity<?> approve(@RequestBody List<Integer> qIds) {
        questionService.approve(qIds);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/archive")
    public ResponseEntity<?> archive(@RequestBody List<Integer> qIds) {
        questionService.archive(qIds);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
