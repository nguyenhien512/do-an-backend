package com.hust.exam.mapper;

import com.hust.exam.DTO.AnswerDto;
import com.hust.exam.models.Answer;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class AnswerMapper {

   @Autowired
   ModelMapper modelMapper;

   @Autowired
   ListMapper listMapper;

   private AnswerDto answerDto;

    public AnswerDto toAnswerDto(Answer answer) {
        return modelMapper.map(answer, AnswerDto.class);
    }

    public List<AnswerDto> toAnswerDtoList(List<Answer> answers) {
        return listMapper.mapList(answers, AnswerDto.class, this::toAnswerDto);
    }

}
