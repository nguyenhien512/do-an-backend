package com.hust.exam.mapper;

import com.hust.exam.DTO.StudentDTO;
import com.hust.exam.DTO.TestDTO;
import com.hust.exam.DTO.TestResultDTO;
import com.hust.exam.models.Question;
import com.hust.exam.models.Test;

import java.util.List;
import java.util.stream.Collectors;

public class TestMapper {
    public static TestDTO testToTestDTO(Test test) {
        TestDTO testDTO = new TestDTO();
        testDTO.setId(test.getId());
        List<Question> questions = test.getTestQuestionRelations().stream().map(item -> item.getQuestion()).collect(Collectors.toList());
        testDTO.setQuestions(QuestionMapper.questionsToQuestionDTOs(questions));
        testDTO.setCreatedDate(test.getCreatedDate());
        testDTO.setStudent(test.getStudent().getUsername());
        return testDTO;
    }

    public static TestResultDTO testToTestResultDTO(Test test) {
        TestResultDTO testResultDTO = new TestResultDTO();
        testResultDTO.setId(test.getId());
        testResultDTO.setScore(test.getScore());
        testResultDTO.setDuration(test.getDuration());
        return testResultDTO;
    }

}
