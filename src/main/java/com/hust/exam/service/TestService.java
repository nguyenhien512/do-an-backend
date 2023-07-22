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

    @Autowired
    ExamService examService;


    public TestDto createTest(String username, int examId) {
        Student student = (Student) userRepository.findById(username).orElseThrow();
        Exam exam = examRepository.findById(examId).orElseThrow();

        if (countByExamAndStudent(exam, student) >= exam.getMaxRetry()) {
            throw new RuntimeException("Student exceed limit to do exam");
        }

        if (!examService.checkExamOpen(exam)) {
            throw new RuntimeException("Exam is not opened or has been closed");
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
            addQuestionToTest(test, question, mappingRule, i);
        }
        Test saved = testRepository.save(test);
        Test buildTest = buildTest(saved, false);
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
            if (checkAnswer(r.getQuestion(), mappedAnswer)) {
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
        Test build = buildTest(foundTest, true);
        return testMapper.toTestDto(build);
    }

    public List<TestResultDto> getResultByExam(int examId) {
        return testMapper.toTestResultDtoList(testRepository.findByExam(examId));
    }

    public int countByExamAndStudent(Exam exam, Student student) {
        return testRepository.countByExamAndStudent(exam, student);
    }

    public Test buildTest (Test test, boolean showCorrectAnswer) {
        List<TestQuestionRelation> shuffleRelations = new ArrayList<>();

        for (TestQuestionRelation relation : test.getTestQuestionRelations()) {
            TestQuestionRelation newRelation = new TestQuestionRelation();

            MappingRule mappingRule = relation.getMappingRule();
            Question question = relation.getQuestion();

            List<Answer> shuffleAnswers = new ArrayList<>();

            for (Answer answer : question.getAnswers()) {
                Answer newAnswer = cloneAnswerAndReplaceKey(answer, mappingRule);
                shuffleAnswers.add(newAnswer);
            }

            Question newQuestion = cloneQuestionAndReplaceAnswers(question, shuffleAnswers, showCorrectAnswer, mappingRule);
            newRelation.setQuestion(newQuestion);
            newRelation.setAnswers(relation.getAnswers());
            newRelation.setQuestionIndex(relation.getQuestionIndex());
            shuffleRelations.add(newRelation);
        }

        shuffleRelations.sort(Comparator.comparingInt(TestQuestionRelation::getQuestionIndex));
        return cloneTestAndReplaceTestQuestionRelations(test, shuffleRelations);
    }

    private void addQuestionToTest(Test test, Question question, MappingRule mappingRule, int questionIndex) {
        TestQuestionRelation relation = new TestQuestionRelation();
        relation.setTest(test);
        relation.setQuestion(question);
        relation.setMappingRule(mappingRule);
        relation.setQuestionIndex(questionIndex);
        test.getTestQuestionRelations().add(relation);
    }

    private Test cloneTestAndReplaceTestQuestionRelations(Test test, List<TestQuestionRelation> newRelations) {
        Test clone = new Test();
        clone.setId(test.getId());
        clone.setStudent(test.getStudent());
        clone.setExam(test.getExam());
        clone.setScore(test.getScore());
        clone.setCreateTime(test.getCreateTime());
        clone.setSubmitTime(test.getSubmitTime());
        clone.setHasSubmit(test.isHasSubmit());
        clone.setTestQuestionRelations(newRelations);
        return clone;
    }

    private Answer cloneAnswerAndReplaceKey(Answer answer, MappingRule mappingRule) {
        AnswerKey mappedKey = MappingUtil.mapForward(answer.getKey(),mappingRule);
        Answer clone = new Answer();
        clone.setContent(answer.getContent());
        clone.setKey(mappedKey);
        return clone;
    }

    private boolean checkAnswer(Question question, String chosenAnswers) {
        return question.getCorrectAnswers().equals(chosenAnswers);
    }

    private Question cloneQuestionAndReplaceAnswers(Question question, List<Answer> newAnswers, boolean showCorrectAnswer, MappingRule mappingRule) {
        Question clone = new Question();
        clone.setId(question.getId());
        clone.setContent(question.getContent());
        clone.setAnswers(newAnswers);
        if (showCorrectAnswer) {
            String mappedCorrectAnswer = MappingUtil.mapForward(question.getCorrectAnswers(),mappingRule);
            clone.setCorrectAnswers(mappedCorrectAnswer);
        }
        return clone;
    }

}
