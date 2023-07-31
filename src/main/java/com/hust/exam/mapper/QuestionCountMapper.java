package com.hust.exam.mapper;

import com.hust.exam.DTO.QuestionCountDto;
import com.hust.exam.models.QuestionCount;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class QuestionCountMapper {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    ListMapper listMapper;

    public QuestionCountDto toQuestionCountDto (QuestionCount entity) {
    return modelMapper.map(entity, QuestionCountDto.class);
    }

    public List<QuestionCountDto> toQuestionCountDtoList (List<QuestionCount> entities) {
        return listMapper.mapList(entities, QuestionCountDto.class, this::toQuestionCountDto);
    }

}
