package com.hust.exam.mapper;

import com.hust.exam.DTO.ExamCountDto;
import com.hust.exam.DTO.ExamDto;
import com.hust.exam.models.Exam;
import com.hust.exam.models.ExamCount;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ExamMapper {
    @Autowired
    ModelMapper modelMapper;

    @Autowired
    ListMapper listMapper;

    @Autowired
    QuestionMapper questionMapper;
    public ExamDto toExamDto (Exam exam) {
        ExamDto dto = modelMapper.map(exam, ExamDto.class);
        if(exam.getStudentClass() != null) {
            dto.setStudentClassId(exam.getStudentClass().getId());
            dto.setStudentClassName(exam.getStudentClass().getName());
        }
        if(exam.getQuestions() != null) {
            dto.setQuestionDtoList(questionMapper.toQuestionDtoList(exam.getQuestions()));
        }
        return dto;
    }

    public Exam toExamEntity (ExamDto dto) {
        return modelMapper.map(dto, Exam.class);
    }

    public List<ExamDto> toExamDtoList(List<Exam> exams) {
        return listMapper.mapList(exams, ExamDto.class, this::toExamDto);
    }

    public ExamCountDto toExamCountDto (ExamCount examCount) {
        return modelMapper.map(examCount, ExamCountDto.class);
    }

    public List<ExamCountDto> toExamCountDtoList(List<ExamCount> examCounts) {
        return listMapper.mapList(examCounts, ExamCountDto.class, this::toExamCountDto);
    }
}
