package com.hust.exam.controllers;

import com.hust.exam.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/questions")
@PreAuthorize("hasAuthority('TEACHER')")
@CrossOrigin
public class QuestionController {

    @Autowired
    QuestionService questionService;

//    @GetMapping("")
//    public List<QuestionDto> getAll() {
//        List<Question> questions = questionService.getAll();
//        return QuestionMapper.toQuestionDtoList(questions);
//    }
}
