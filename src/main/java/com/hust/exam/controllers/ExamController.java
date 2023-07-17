package com.hust.exam.controllers;

import com.hust.exam.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/exams")
@CrossOrigin
public class ExamController {

    @Autowired
    ExamService examService;

}
