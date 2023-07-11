package com.hust.exam.service;

import com.hust.exam.DTO.TestDTO;
import com.hust.exam.DTO.TestResultDTO;
import com.hust.exam.DTO.TestSubmitDTO;
import com.hust.exam.enumobject.AnswerType;
import com.hust.exam.mapper.TestMapper;
import com.hust.exam.models.*;
import com.hust.exam.repository.QuestionRepository;
import com.hust.exam.repository.StudentRepository;
import com.hust.exam.repository.TestQuestionRelationRepository;
import com.hust.exam.repository.TestRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class TestService {
    @Autowired
    TestRepository testRepository;

    @Autowired
    QuestionService questionService;

    @Autowired
    TestQuestionRelationRepository testQuestionRelationRepository;

    @Autowired
    StudentRepository studentRepository;


    public Test createTest(String username) {
        Student student = studentRepository.findById(username).get();
        Test test = new Test();
        test.setStudent(student);
        List<Question> questions = questionService.getRandomQuestions(5);
        test.setCreatedDate(LocalDateTime.now());
        test.setQuestions(questions);
        Test saved = testRepository.save(test);
        return saved;
    }

    public void postAnswers(int testId, TestSubmitDTO testWithAnswer) {
        Test foundTest = testRepository.findById(testId).get();
        foundTest.setDuration(testWithAnswer.getDuration());
        Map<Integer, AnswerType> answers = testWithAnswer.getAnswers();
        for (Map.Entry<Integer,AnswerType> answer : answers.entrySet()) {
            int questionId = answer.getKey();
            AnswerType chosenAnswer = answer.getValue();
            TestQuestionRelationId relationId = new TestQuestionRelationId(testId,questionId);
            TestQuestionRelation relation = testQuestionRelationRepository.findById(relationId).get();
            relation.setChosenAnswer(chosenAnswer);
        }
        foundTest.score();
        testRepository.save(foundTest);
    }

    public Test getResult(int testId) {
        return testRepository.findById(testId).get();
    }

}
