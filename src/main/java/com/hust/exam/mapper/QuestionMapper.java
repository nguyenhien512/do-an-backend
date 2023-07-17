package com.hust.exam.mapper;

import com.hust.exam.DTO.AnswerDto;
import com.hust.exam.DTO.QuestionDto;
import com.hust.exam.DTO.StudentQuestionDto;
import com.hust.exam.models.MappingRule;
import com.hust.exam.models.Question;
import com.hust.exam.utils.MappingUtil;
import org.modelmapper.ModelMapper;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class QuestionMapper {

    static ModelMapper modelMapper = new ModelMapper();
    public static QuestionDto toQuestionDto(Question question) {
        return modelMapper.map(question, QuestionDto.class);
    }

    public static QuestionDto toQuestionDto(Question question, MappingRule mappingRule) {
        StudentQuestionDto quickMap = toStudentQuestionDto(question, mappingRule);
        QuestionDto questionDto = new QuestionDto();
        questionDto.setId(quickMap.getId());
        questionDto.setAnswers(quickMap.getAnswers());
        String transformedCorrectAnswers = MappingUtil.mapForward(question.getCorrectAnswers(),mappingRule);
        questionDto.setCorrectAnswers(transformedCorrectAnswers);
        questionDto.setExam_times(question.getExamTimes());
        return questionDto;
    }

    public static StudentQuestionDto toStudentQuestionDto(Question question, List<AnswerDto> answerDtos) {
        StudentQuestionDto dto = new StudentQuestionDto();
        dto.setId(question.getId());
        dto.setContent(question.getContent());
        dto.setAnswers(answerDtos);
        return dto;
    }

    public static List<QuestionDto> toQuestionDtoList(List<Question> questions) {
        return ListMapper.mapList(questions, QuestionDto.class, QuestionMapper::toQuestionDto);
    }

    public static StudentQuestionDto toStudentQuestionDto(Question question, MappingRule mappingRule) {
        List<AnswerDto> answerDtoList = question.getAnswers()
                .stream()
                .map(answer -> AnswerMapper.toAnswerDto(answer, mappingRule))
                .sorted(Comparator.comparing(AnswerDto::getKey))
                .collect(Collectors.toList());
        return toStudentQuestionDto(question, answerDtoList);
    }
    
}
