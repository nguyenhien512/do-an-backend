package com.hust.exam.controllers;

import com.hust.exam.DTO.QuestionDto;
import com.hust.exam.mapper.QuestionMapper;
import com.hust.exam.models.Question;
import com.hust.exam.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/questions")
@PreAuthorize("hasAuthority('TEACHER')")
@CrossOrigin
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @GetMapping("")
    public List<QuestionDto> getAll() {
        List<Question> questions = questionService.getAll();
        return QuestionMapper.toQuestionDtoList(questions);
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuestionDto> getById(@PathVariable("id") int id) {
        Question question = questionService.findById(id);
        return new ResponseEntity<>(QuestionMapper.toQuestionDto(question), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<QuestionDto> createQuestion(@RequestBody QuestionDto dto) {
        Question question = questionService.createQuestion(dto);
        return new ResponseEntity<>(QuestionMapper.toQuestionDto(question), HttpStatus.CREATED);
    }

    @PutMapping("/edit")
    public ResponseEntity<QuestionDto> editQuestion(@RequestBody QuestionDto dto) {
        Question question = questionService.editQuestion(dto);
        return new ResponseEntity<>(QuestionMapper.toQuestionDto(question), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteQuestion(@PathVariable("id") int id) {
        questionService.deleteQuestion(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
