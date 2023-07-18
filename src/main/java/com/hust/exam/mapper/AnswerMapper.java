package com.hust.exam.mapper;

import com.hust.exam.DTO.AnswerDto;
import com.hust.exam.enumobject.AnswerKey;
import com.hust.exam.models.Answer;
import com.hust.exam.models.MappingRule;
import com.hust.exam.utils.MappingUtil;
import org.modelmapper.ModelMapper;

import java.util.List;
public class AnswerMapper {

    static ModelMapper modelMapper = new ModelMapper();
    static {
        modelMapper.getConfiguration().setSkipNullEnabled(true);
    }
    public static AnswerDto toAnswerDto(Answer answer) {
        return modelMapper.map(answer, AnswerDto.class);
    }

    public static AnswerDto toAnswerDto(Answer answer, MappingRule mappingRule) {
        AnswerDto answerDto = new AnswerDto();
        AnswerKey transformedAnswerKey = MappingUtil.mapForward(answer.getKey(), mappingRule);
        answerDto.setKey(transformedAnswerKey);
        answerDto.setContent(answer.getContent());
        return answerDto;
    }

    public static List<AnswerDto> toAnswerDtoList(List<Answer> answers) {
        return ListMapper.mapList(answers, AnswerDto.class, AnswerMapper::toAnswerDto);
    }

    public static Answer toAnswerEntity(AnswerDto dto) {return modelMapper.map(dto, Answer.class);}

    public static List<Answer> toAnswerList(List<AnswerDto> dtos) {
        return ListMapper.mapList(dtos, Answer.class, AnswerMapper::toAnswerEntity);
    }
}
