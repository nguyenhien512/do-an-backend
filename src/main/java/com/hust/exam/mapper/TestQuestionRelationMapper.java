package com.hust.exam.mapper;

import com.hust.exam.DTO.TestQuestionRelationDto;
import com.hust.exam.models.TestQuestionRelation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TestQuestionRelationMapper {
    @Autowired
    ModelMapper modelMapper;

    @Autowired
    ListMapper listMapper;

    public TestQuestionRelationDto toTestQuestionRelationDto (TestQuestionRelation relation) {
        return modelMapper.map(relation, TestQuestionRelationDto.class);
    }

    public List<TestQuestionRelationDto> toTestDtoList (List<TestQuestionRelation> tests) {
        return listMapper.mapList(tests, TestQuestionRelationDto.class, this::toTestQuestionRelationDto);
    }
}
