package com.hust.exam.mapper;

import com.hust.exam.DTO.AnswerDto;
import com.hust.exam.DTO.QuestionDto;
import com.hust.exam.DTO.StudentQuestionDto;
import com.hust.exam.models.Question;
import org.modelmapper.ModelMapper;

import java.util.List;

public class QuestionMapper {

    static ModelMapper modelMapper = new ModelMapper();
    public static QuestionDto toQuestionDto(Question question) {
        return modelMapper.map(question, QuestionDto.class);
    }

    public static StudentQuestionDto toShortQuestionDto (Question question, List<AnswerDto> answerDtos) {
        StudentQuestionDto shortQuestionDto = new StudentQuestionDto();
        shortQuestionDto.setId(question.getId());
        shortQuestionDto.setContent(question.getContent());
        shortQuestionDto.setAnswers(answerDtos);
        return shortQuestionDto;
    }

    public static List<QuestionDto> toQuestionDtoList(List<Question> questions) {
        return ListMapper.mapList(questions, QuestionDto.class, QuestionMapper::toQuestionDto);
    }
    
}
