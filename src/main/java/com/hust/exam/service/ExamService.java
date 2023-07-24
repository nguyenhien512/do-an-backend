package com.hust.exam.service;

import com.hust.exam.DTO.ExamDto;
import com.hust.exam.enumobject.ExamStatus;
import com.hust.exam.mapper.ExamMapper;
import com.hust.exam.models.Exam;
import com.hust.exam.repository.ExamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ExamService {

    @Autowired
    ExamRepository examRepository;

    @Autowired
    ExamMapper examMapper;

    public List<ExamDto> findAll (){
        return examMapper.toExamDtoList(examRepository.findAll());
    }
    public List<ExamDto> findPublishedExamsByStudent (String username) {
        return examMapper.toExamDtoList(examRepository.findExamsByStudent(username, ExamStatus.PUBLISHED));
    }

    public List<ExamDto> findByTeacher (String username) {
        return examMapper.toExamDtoList(examRepository.findByTeacherUsername(username));
    }

    public boolean checkExamOpen(Exam exam) {
        LocalDateTime now = LocalDateTime.now();
        return exam.getOpenTime().isBefore(now) && exam.getCloseTime().isAfter(now);
    }
}
