package com.hust.exam.mapper;

import com.hust.exam.DTO.AnswerDto;
import com.hust.exam.DTO.ExamQuestionDto;
import com.hust.exam.DTO.ExamTestDto;
import com.hust.exam.DTO.TestResultDto;
import com.hust.exam.models.Question;
import com.hust.exam.models.Test;
import com.hust.exam.models.TestQuestionRelation;
import org.modelmapper.ModelMapper;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class TestMapper {
    static ModelMapper modelMapper = new ModelMapper();
    static {
        modelMapper.getConfiguration().setSkipNullEnabled(true);
    }
    public static ExamTestDto toExamTestDto (Test test) {
        ExamTestDto examTestDto = new ExamTestDto();
        examTestDto.setId(test.getId());
        examTestDto.setCreateTime(test.getCreateTime());
        List<TestQuestionRelation> sortedTestQuestionRelations = test.getTestQuestionRelations()
                .stream()
                .sorted(Comparator.comparingInt(TestQuestionRelation::getQuestionIndex))
                .toList();
        List<ExamQuestionDto> questionDtoList = sortedTestQuestionRelations
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
