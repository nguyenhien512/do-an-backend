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
@PreAuthorize("hasAuthority('TEACHER') or hasAuthority('ADMIN')")
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
       return new ResponseEntity<TopicDto>(topicService.createTopic(dto), HttpStatus.CREATED);
    }

    @PutMapping("")
    public ResponseEntity<TopicDto> updateTopic(@RequestBody TopicDto dto) {
        return new ResponseEntity<TopicDto>(topicService.updateTopic(dto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTopic(@PathVariable int id) {
         topicService.deleteTopic(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
