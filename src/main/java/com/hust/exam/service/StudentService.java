package com.hust.exam.service;

import com.hust.exam.DTO.StudentDto;
import com.hust.exam.mapper.StudentMapper;
import com.hust.exam.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    StudentMapper studentMapper;

    @Autowired
    StudentRepository studentRepository;

    public List<StudentDto> searchByNameOrUsername(String queryString) {
        return studentMapper.toStudentDtoList(studentRepository.searchByNameOrUsername(queryString));
    }
}
