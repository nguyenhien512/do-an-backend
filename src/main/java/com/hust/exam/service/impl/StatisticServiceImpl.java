package com.hust.exam.service.impl;

import com.hust.exam.DTO.StatisticRecord;
import com.hust.exam.repository.ExamRepository;
import com.hust.exam.service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class StatisticServiceImpl implements StatisticService {

    @Autowired
    ExamRepository examRepository;
    @Override
    public List<StatisticRecord> getDataByExam(int examId) {
        List<StatisticRecord> records = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            int studentNum = 0;
            if (i != 9) {
                studentNum = examRepository.findStudentNumByPoint(examId, i + 1, i);
            } else {
                studentNum = examRepository.findStudentNumByPoint10(examId);
            }
            System.out.println("Studentnum is: " + studentNum);
            if(studentNum != 0) {
                records.add(new StatisticRecord(i, studentNum));
            }
        }
        return records;
    }

}
