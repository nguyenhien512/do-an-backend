package com.hust.exam.mapper;

import com.hust.exam.DTO.*;
import com.hust.exam.models.Question;
import com.hust.exam.models.Test;
import org.modelmapper.ModelMapper;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class TestMapper {
    static ModelMapper modelMapper = new ModelMapper();
    static {
        modelMapper.getConfiguration().setSkipNullEnabled(true);
    }
    public static StudentTestDto toExamTestDto (Test test) {
        StudentTestDto examTestDto = new StudentTestDto();
        examTestDto.setId(test.getId());
        examTestDto.setCreateTime(test.getCreateTime());
        List<StudentQuestionDto> questionDtoList = test.getTestQuestionRelations()
                .stream()
                .map(relation -> {
                    Question question = relation.getQuestion();
                    List<AnswerDto> answerDtoList = question.getAnswers()
                            .stream()
                            .map(answer -> AnswerMapper.toAnswerDto(answer, relation.getMappingRule()))
                            .sorted(Comparator.comparing(AnswerDto::getKey))
                            .collect(Collectors.toList());
                    return QuestionMapper.toShortQuestionDto(question, answerDtoList);
                })
                .collect(Collectors.toList());
        examTestDto.setQuestions(questionDtoList);
        return examTestDto;
    }

    public static TestResultDto toTestResultDto (Test test) {
        return modelMapper.map(test, TestResultDto.class);
    }

}
