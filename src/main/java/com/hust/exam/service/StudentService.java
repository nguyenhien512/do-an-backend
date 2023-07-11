package com.hust.exam.service;

import com.hust.exam.DTO.QuestionDTO;
import com.hust.exam.mapper.QuestionMapper;
import com.hust.exam.models.Question;
import com.hust.exam.models.Student;
import com.hust.exam.repository.QuestionRepository;
import com.hust.exam.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    public Student getStudent(String username) {
        return studentRepository.findById(username).get();
    }

}
