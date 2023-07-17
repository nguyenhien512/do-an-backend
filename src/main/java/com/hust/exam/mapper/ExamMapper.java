package com.hust.exam.mapper;

import com.hust.exam.DTO.StudentExamDto;
import com.hust.exam.DTO.TeacherExamDto;
import com.hust.exam.models.Exam;
import org.modelmapper.ModelMapper;

import java.util.List;

public class ExamMapper {
    static ModelMapper modelMapper = new ModelMapper();
    static {
        modelMapper.getConfiguration().setSkipNullEnabled(true);
    }
    public static StudentExamDto toStudentExamDto (Exam exam) {
        return modelMapper.map(exam, StudentExamDto.class);
    }

    public static List<StudentExamDto> toStudentExamDtoList(List<Exam> exams) {
        return ListMapper.mapList(exams, StudentExamDto.class, ExamMapper::toStudentExamDto);
    }

    public static TeacherExamDto toTeacherExamDto (Exam exam) {
        return modelMapper.map(exam, TeacherExamDto.class);
    }

    public static List<TeacherExamDto> toTeacherExamDtoList(List<Exam> exams) {
        return ListMapper.mapList(exams, TeacherExamDto.class, ExamMapper::toTeacherExamDto);
    }
}
