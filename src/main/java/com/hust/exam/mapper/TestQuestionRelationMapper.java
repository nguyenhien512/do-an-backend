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
        return modelMapper.map(relation, TestQuestionRelationDto.class);
    }
}
