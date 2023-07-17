package com.hust.exam.service;

import com.hust.exam.models.*;
import com.hust.exam.repository.TestRepository;
import com.hust.exam.utils.MappingUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;

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
            test.addQuestion(question, mappingRule);
            question.setExamTimes(question.getExamTimes() + 1);
        }
        return testRepository.save(test);
    }

    public void postAnswers(String username, int testId, Map<Integer, String> answers) {
        Test found = testRepository.findById(testId).get();
        if (found.isHasSubmit()) {
            return;
        }
        if (!found.getStudent().getUsername().equals(username)) {
            return;
        }
        found.setSubmitTime(LocalDateTime.now());
        found.setHasSubmit(true);
        for (Map.Entry<Integer, String> entry : answers.entrySet()) {
            int questionId = entry.getKey();
            String answer = entry.getValue();
            TestQuestionRelation relation = found.getTestQuestionRelations()
                    .stream()
                    .filter(r -> r.getQuestion().getId() == questionId)
                    .toList()
                    .get(0);
            relation.setAnswers(answer);
        }
        int score = calculateScore(found);
        found.setScore(score);
        testRepository.save(found);
    }

    public int calculateScore(Test test) {
        int score = 0;
        for (TestQuestionRelation r : test.getTestQuestionRelations()) {
            String mappedAnswer = MappingUtil.mapBackward(r.getAnswers(), r.getMappingRule());
            if (r.getQuestion().checkAnswer(mappedAnswer)) {
                score += 1;
            }
        }
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
