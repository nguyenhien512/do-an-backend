package com.hust.exam.mapper;

import com.hust.exam.DTO.QuestionDto;
import com.hust.exam.models.Question;
import org.modelmapper.ModelMapper;

import java.util.List;

public class QuestionMapper {

    static ModelMapper modelMapper = new ModelMapper();
    public static QuestionDto toQuestionDto(Question question) {
        QuestionDto dto = modelMapper.map(question, QuestionDto.class);
        return dto;
    }

    public static List<QuestionDto> toQuestionDtoList(List<Question> questions) {
        return ListMapper.mapList(questions, QuestionDto.class,question -> toQuestionDto(question));
    }
    
}
