package com.hust.exam.controllers;

import com.hust.exam.DTO.QuestionDto;
import com.hust.exam.mapper.QuestionMapper;
import com.hust.exam.models.Question;
import com.hust.exam.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/questions")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @GetMapping("")
    public List<QuestionDto> getAll() {
        List<Question> questions = questionService.getAll();
        return QuestionMapper.toQuestionDtoList(questions);
    }
}
