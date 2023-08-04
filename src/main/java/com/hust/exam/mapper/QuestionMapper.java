package com.hust.exam.mapper;

import com.hust.exam.DTO.QuestionDto;
import com.hust.exam.models.Question;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
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

    public Question toQuestionEntity(QuestionDto dto) {
        TypeMap<QuestionDto, Question> propertyMapper = modelMapper.getTypeMap(QuestionDto.class, Question.class);
        if (propertyMapper == null) {
            propertyMapper = modelMapper.createTypeMap(QuestionDto.class, Question.class);
        }
        propertyMapper.addMappings(mapper -> {
            mapper.skip(QuestionDto::getCreateByUsername,Question::setCreateBy);
            mapper.skip(QuestionDto::getExamTimes,Question::setExamTimes);
            mapper.skip(QuestionDto::getStatus,Question::setStatus);
        }
    );
        return modelMapper.map(dto, Question.class);
    }

    public List<Question> toQuestionEntityList(List<QuestionDto> dtos) {
        return listMapper.mapList(dtos, Question.class, this::toQuestionEntity);
    }
    
}
