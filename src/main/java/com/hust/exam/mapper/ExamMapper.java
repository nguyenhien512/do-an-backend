package com.hust.exam.mapper;

import com.hust.exam.DTO.ExamDto;
import com.hust.exam.models.Exam;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class ExamMapper {
    @Autowired
    ModelMapper modelMapper;

    @Autowired
    ListMapper listMapper;
    public ExamDto toExamDto (Exam exam) {
        TypeMap<Exam, ExamDto> propertyMapper = modelMapper.getTypeMap(Exam.class, ExamDto.class);
        if (propertyMapper == null) {
            propertyMapper = modelMapper.createTypeMap(Exam.class, ExamDto.class);
        }
        Converter<LocalDateTime, Boolean> openTimeConverter = date -> date.getSource().isBefore(LocalDateTime.now());
        Converter<LocalDateTime, Boolean> closeTimeConverter = date -> date.getSource().isAfter(LocalDateTime.now());
        propertyMapper.addMappings(mapper -> {
            mapper.using(openTimeConverter).map(Exam::getOpenTime, ExamDto::setAfterOpen);
            mapper.using(closeTimeConverter).map(Exam::getCloseTime, ExamDto::setBeforeClose);
        });
        return modelMapper.map(exam, ExamDto.class);
    }

    public List<ExamDto> toExamDtoList(List<Exam> exams) {
        return listMapper.mapList(exams, ExamDto.class, this::toExamDto);
    }
}
