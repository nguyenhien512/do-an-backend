package com.hust.exam.mapper;

import com.hust.exam.DTO.ExamDto;
import com.hust.exam.models.Exam;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;

public class ExamMapper {
    static ModelMapper modelMapper = new ModelMapper();
    static {
        modelMapper.getConfiguration().setSkipNullEnabled(true);
    }
    public static ExamDto toExamDto (Exam exam) {
        TypeMap<Exam, ExamDto> propertyMapper = modelMapper.createTypeMap(Exam.class, ExamDto.class);
        propertyMapper.addMapping(Exam::getQuestions, ExamDto::setQuestions);
        return modelMapper.map(exam, ExamDto.class);
    }
}
