package com.hust.exam.mapper;

import com.hust.exam.DTO.StudentDto;
import com.hust.exam.models.Student;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StudentMapper {
    @Autowired
    ModelMapper modelMapper;

    @Autowired
    ListMapper listMapper;
    public StudentDto toStudentDto (Student student) {
        return modelMapper.map(student, StudentDto.class);
    }

    public List<StudentDto> toStudentDtoList(List<Student> students) {
        return listMapper.mapList(students, StudentDto.class, this::toStudentDto);
    }
}
