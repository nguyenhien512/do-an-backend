package com.hust.exam.mapper;

import com.hust.exam.DTO.TestQuestionRelationDto;
import com.hust.exam.models.TestQuestionRelation;
import org.modelmapper.ModelMapper;

public class TestQuestionRelationMapper {
    static ModelMapper modelMapper = new ModelMapper();
    static {
        modelMapper.getConfiguration().setSkipNullEnabled(true);
    }
    public static TestQuestionRelationDto toDto (TestQuestionRelation relation) {
        TestQuestionRelationDto dto = new TestQuestionRelationDto();
        dto.setQuestion(QuestionMapper.toQuestionDto(relation.getQuestion(),relation.getMappingRule()));
        dto.setAnswers(relation.getAnswers());
        return dto;
    }
}
