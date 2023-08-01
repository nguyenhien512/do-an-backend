package com.hust.exam.controllers;

import com.hust.exam.DTO.TopicDto;
import com.hust.exam.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("")
    public ResponseEntity<TopicDto> createTopic(@RequestBody TopicDto dto) {
       return new ResponseEntity<TopicDto>(topicService.createTopic(dto), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TopicDto> updateTopic(@PathVariable int id, @RequestBody TopicDto dto) {
        return new ResponseEntity<TopicDto>(topicService.updateTopic(id, dto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTopic(@PathVariable int id) {
         topicService.deleteTopic(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
