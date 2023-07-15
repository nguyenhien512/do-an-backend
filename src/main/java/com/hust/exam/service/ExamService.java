package com.hust.exam.service;

import com.hust.exam.models.Exam;
import com.hust.exam.repository.ExamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExamService {

    @Autowired
    ExamRepository examRepository;

    public Exam findById(int id) {
        return examRepository.findById(id).get();
    }
}
