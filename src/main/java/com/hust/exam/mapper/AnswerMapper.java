package com.hust.exam.mapper;

import com.hust.exam.DTO.AnswerDto;
import com.hust.exam.models.Answer;
import org.modelmapper.ModelMapper;

import java.util.List;
public class AnswerMapper {

    static ModelMapper modelMapper = new ModelMapper();
    public static AnswerDto toAnswerDto(Answer answer) {
        return modelMapper.map(answer, AnswerDto.class);
    }

    public static List<AnswerDto> toAnswerDtoList(List<Answer> answers) {
        return ListMapper.mapList(answers, AnswerDto.class,answer -> toAnswerDto(answer));
    }


}
