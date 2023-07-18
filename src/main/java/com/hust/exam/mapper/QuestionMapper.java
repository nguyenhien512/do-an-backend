package com.hust.exam.mapper;

import com.hust.exam.DTO.QuestionDto;
import com.hust.exam.models.Question;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class QuestionMapper {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    ListMapper listMapper;

    public QuestionDto toQuestionDto (Question question) {
        return modelMapper.map(question, QuestionDto.class);
    }

    public List<QuestionDto> toQuestionDtoList (List<Question> questions) {
        return listMapper.mapList(questions, QuestionDto.class, this::toQuestionDto);
    }


    
}
