package com.hust.exam.mapper;

import com.hust.exam.DTO.*;
import com.hust.exam.models.Test;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class TestMapper {
    static ModelMapper modelMapper = new ModelMapper();
    static {
        modelMapper.getConfiguration().setSkipNullEnabled(true);
    }
    public static StudentTestDto toExamTestDto (Test test) {
        StudentTestDto dto = new StudentTestDto();
        dto.setId(test.getId());
        dto.setCreateTime(test.getCreateTime());
        List<StudentQuestionDto> questionDtoList = test.getTestQuestionRelations()
                .stream()
                .map(relation -> {
                    return QuestionMapper.toStudentQuestionDto(relation.getQuestion(), relation.getMappingRule());
                })
                .collect(Collectors.toList());
        dto.setQuestions(questionDtoList);
        return dto;
    }

    public static TestResultDto toTestResultDto (Test test) {
        return modelMapper.map(test, TestResultDto.class);
    }

    public static TeacherTestDto toTeacherTestDto (Test test) {
        TeacherTestDto dto = new TeacherTestDto();
        dto.setId(test.getId());
        dto.setCreateTime(test.getCreateTime());
        dto.setStudentUsername(test.getStudent().getUsername());
        dto.setExamId(test.getExam().getId());
        dto.setScore(test.getScore());
        dto.setSubmitTime(test.getSubmitTime());
        dto.setHasSubmit(test.isHasSubmit());
        List<TestQuestionRelationDto> relationDtoList = test.getTestQuestionRelations()
                .stream()
                .map(TestQuestionRelationMapper::toDto)
                .collect(Collectors.toList());
        dto.setTestQuestionRelations(relationDtoList);
        return dto;
    }

    public static List<TeacherTestDto> toTeacherTestDtoList (List<Test> tests) {
        return ListMapper.mapList(tests, TeacherTestDto.class, TestMapper::toTeacherTestDto);
    }

}
