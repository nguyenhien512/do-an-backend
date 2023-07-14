package com.hust.exam.controllers;

import com.hust.exam.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/api/v1/tests")

public class TestController {

    @Autowired
    TestService testService;

//    @PostMapping("")
//    public TestDto createTest() throws JsonProcessingException {
//        String username = "hien1";
//        return TestMapper.testToTestDTO(testService.createTest(username));
//    }
//
//    @PostMapping ("/{testId}/answers")
//    public void postAnswers(@PathVariable int testId, @RequestBody String body) throws JsonProcessingException {
//        TestSubmitDto testSubmitDTO = JsonHelper.convertJsonToTestSubmitDTO(body);
//        testService.postAnswers(testId, testSubmitDTO);
//    }
//
//    @GetMapping("/{testId}/result")
//    public TestResultDto getResult(@PathVariable int testId) {
//        return TestMapper.testToTestResultDTO(testService.getResult(testId));
//    }
}
