package com.hust.exam.service;

import com.hust.exam.DTO.TestDto;
import com.hust.exam.DTO.TestResultDto;
import com.hust.exam.DTO.TestSubmitDto;
import com.hust.exam.enumobject.AnswerKey;
import com.hust.exam.mapper.TestMapper;
import com.hust.exam.models.*;
import com.hust.exam.repository.ExamRepository;
import com.hust.exam.repository.TestRepository;
import com.hust.exam.repository.UserRepository;
import com.hust.exam.utils.MappingUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class TestService {
    @Autowired
    TestRepository testRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ExamRepository examRepository;

    @Autowired
    MappingRuleService mappingRuleService;

    @Autowired
    TestMapper testMapper;


    public TestDto createTest(String username, int examId) {
        Student student = (Student) userRepository.findById(username).orElseThrow();
        Exam exam = examRepository.findById(examId).orElseThrow();

        if (countByExamAndStudent(exam, student) >= exam.getMaxRetry()) {
            throw new RuntimeException("Student exceed limit to do exam");
        }

        Test test = new Test();
        test.setExam(exam);
        //update exam times of exam
        exam.setExamTimes(exam.getExamTimes() + 1);
        test.setStudent(student);
        test.setCreateTime(LocalDateTime.now());

        List<Question> questions = exam.getQuestions();
        Collections.shuffle(questions);

        //shuffle answers
        for (int i = 0; i < questions.size(); i++) {
            Question question = questions.get(i);
            MappingRule mappingRule = mappingRuleService.getRandomRule();
            test.addQuestion(question, mappingRule, i);
            //update exam times of question
            question.setExamTimes(question.getExamTimes() + 1);
        }
        Test saved = testRepository.save(test);
        Test buildTest = buildTest(saved);
        return testMapper.toTestDto(buildTest);
    }

    public void postAnswers(String username, int testId, TestSubmitDto testSubmitDto) {
        Test found = testRepository.findById(testId).orElseThrow();
        if (found.isHasSubmit()) {
            throw new RuntimeException("Re-post answer is not allowed");
        }
        if (!found.getStudent().getUsername().equals(username)) {
            throw new RuntimeException("This student is not the owner of the test. Post answer is not allowed.");
        }
        found.setSubmitTime(LocalDateTime.now());
        found.setHasSubmit(true);
        //save chosen answers of test_question_relation
        for (Map.Entry<Integer, String> entry : testSubmitDto.getAnswers().entrySet()) {
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

    public TestResultDto getResult(String username, int testId) {
        Test foundTest = testRepository.findById(testId).orElseThrow();
        if (!foundTest.getStudent().getUsername().equals(username)) {
            throw new RuntimeException("This student is not the owner of the test. Get result is not allowed.");
        }
        return testMapper.toTestResultDto(foundTest);
    }

    public TestDto getDetail( int testId) {
        Test foundTest = testRepository.findById(testId).orElseThrow();
        return testMapper.toTestDto(foundTest);
    }

    public List<TestResultDto> getResultByExam(int examId) {
        return testMapper.toTestResultDtoList(testRepository.findByExam(examId));
    }

    public int countByExamAndStudent(Exam exam, Student student) {
        return testRepository.countByExamAndStudent(exam, student);
    }

    public Test buildTest (Test test) {
        List<TestQuestionRelation> shuffleRelations = new ArrayList<>();

        for (TestQuestionRelation relation : test.getTestQuestionRelations()) {
            TestQuestionRelation newRelation = new TestQuestionRelation();

            MappingRule mappingRule = relation.getMappingRule();
            Question question = relation.getQuestion();

            List<Answer> shuffleAnswers = new ArrayList<>();

            for (Answer answer : question.getAnswers()) {
                AnswerKey newKey = MappingUtil.mapForward(answer.getKey(),mappingRule);
                Answer newAnswer = answer.cloneAndReplaceKey(newKey);
                shuffleAnswers.add(newAnswer);
            }

            Question newQuestion = question.cloneAndReplaceAnswers(shuffleAnswers);
            newRelation.setQuestion(newQuestion);
            newRelation.setAnswers(relation.getAnswers());
            shuffleRelations.add(newRelation);
        }

        shuffleRelations.sort(Comparator.comparingInt(TestQuestionRelation::getQuestionIndex));
        return test.cloneAndReplaceTestQuestionRelations(shuffleRelations);
    }

}
