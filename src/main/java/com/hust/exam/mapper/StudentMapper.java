package com.hust.exam.mapper;

import com.hust.exam.DTO.QuestionDTO;
import com.hust.exam.DTO.StudentDTO;
import com.hust.exam.models.Question;
import com.hust.exam.models.Student;

import java.util.stream.Collectors;

public class StudentMapper {

    public static StudentDTO studentToStudentDTO(Student student) {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setTests(student.getTests().stream().map(e -> e.getId()).collect(Collectors.toList()));
        studentDTO.setUsername(student.getUsername());
        return studentDTO;
    }
}
