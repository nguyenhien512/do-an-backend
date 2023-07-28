package com.hust.exam.controllers;

import com.hust.exam.DTO.QuestionDto;
import com.hust.exam.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/questions")
@PreAuthorize("hasAuthority('TEACHER')")
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
        System.out.println("Question dto : "+dto);
        return new ResponseEntity<>(questionService.createQuestion(dto), HttpStatus.CREATED);
    }

    @PutMapping("/edit")
    public ResponseEntity<QuestionDto> editQuestion(@RequestBody QuestionDto dto) {
        return new ResponseEntity<>(questionService.editQuestion(dto), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteQuestion(@PathVariable("id") int id) {
        System.out.println("question to delete id "+id);
        questionService.deleteQuestion(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/getByExam")
    public List<QuestionDto> getQuestionsByExam(@RequestParam int examId){
        return questionService.getQuestionsByExam(examId);
    }

    @GetMapping("/getBySearch")
    public List<QuestionDto> getQuestionsBySearchKey(@RequestParam String searchKey){
        return questionService.searchByContent(searchKey);
    }

    @GetMapping("/sorted")
    public List<QuestionDto> sortQuestions(@RequestParam String attribute){
        return questionService.sortByAttribute(attribute);
    }

}
