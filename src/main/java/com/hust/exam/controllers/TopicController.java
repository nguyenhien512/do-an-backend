package com.hust.exam.controllers;

import com.hust.exam.DTO.TopicDto;
import com.hust.exam.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/topics")
@PreAuthorize("hasAuthority('TEACHER')")
@CrossOrigin
public class TopicController {

    @Autowired
    TopicService topicService;

    @GetMapping("")
    public List<TopicDto> getAll() {
        return topicService.getAll();
    }

}
