package com.hust.exam.service;

import com.hust.exam.DTO.ExamDto;
import com.hust.exam.mapper.ExamMapper;
import com.hust.exam.repository.ExamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamService {

    @Autowired
    ExamRepository examRepository;

    @Autowired
    ExamMapper examMapper;

    public List<ExamDto> findByStudent (String username) {
        return examMapper.toExamDtoList(examRepository.findByStudentUsername(username));
    }

    public List<ExamDto> findByTeacher (String username) {
        return examMapper.toExamDtoList(examRepository.findByTeacherUsername(username));
    }
}
