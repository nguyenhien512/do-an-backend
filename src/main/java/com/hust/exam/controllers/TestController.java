package com.hust.exam.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.hust.exam.DTO.TestDTO;
import com.hust.exam.DTO.TestResultDTO;
import com.hust.exam.DTO.TestSubmitDTO;
import com.hust.exam.mapper.TestMapper;
import com.hust.exam.service.TestService;
import com.hust.exam.utils.JsonHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/api/v1/tests")

public class TestController {

    @Autowired
    TestService testService;

    @PostMapping("")
    public TestDTO createTest() throws JsonProcessingException {
        String username = "hien1";
        return TestMapper.testToTestDTO(testService.createTest(username));
    }

    @PostMapping ("/{testId}/answers")
    public void postAnswers(@PathVariable int testId, @RequestBody String body) throws JsonProcessingException {
        TestSubmitDTO testSubmitDTO = JsonHelper.convertJsonToTestSubmitDTO(body);
        testService.postAnswers(testId, testSubmitDTO);
    }

    @GetMapping("/{testId}/result")
    public TestResultDTO getResult(@PathVariable int testId) {
        return TestMapper.testToTestResultDTO(testService.getResult(testId));
    }
}
