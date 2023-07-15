package com.hust.exam.service;

import com.hust.exam.DTO.TestDto;
import com.hust.exam.models.*;
import com.hust.exam.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Service
public class TestService {
    @Autowired
    TestRepository testRepository;

    @Autowired
    UserService userService;

    @Autowired
    ExamService examService;

    @Autowired
    MappingRuleService mappingRuleService;


    public Test createTest(String username, int examId) {
        Student student = (Student) userService.findWithUsername(username);
        Exam exam = examService.findById(examId);
        Test test = new Test();

        test.setExam(exam);
        test.setStudent(student);
        test.setCreateTime(LocalDateTime.now());
        //shuffle questions
        List<Question> questions = exam.getQuestions();
        Collections.shuffle(questions);
        //shuffle answers
        for (int i = 0; i < questions.size(); i++) {
            Question question = questions.get(i);
            MappingRule mappingRule = mappingRuleService.getRandomRule();
            test.addQuestion(question, i, mappingRule);
        }
        return testRepository.save(test);
    }

    public void postAnswers(String username, TestDto testWithAnswer) {
        //post answer here
    }

    public int calculateScore(Test test) {
        int score = 10;
        //calculate score here
        return score;
    }

    public Test getResult(String username, int testId) {
        Test foundTest = testRepository.findById(testId).get();
        if (!foundTest.getStudent().getUsername().equals(username)) {
            return null;
        }
        return foundTest;
    }
}
