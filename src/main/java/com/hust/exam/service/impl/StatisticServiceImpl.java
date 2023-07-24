package com.hust.exam.service.impl;

import com.hust.exam.DTO.StatisticRecord;
import com.hust.exam.repository.ExamRepository;
import com.hust.exam.repository.TestQuestionRelationRepository;
import com.hust.exam.repository.TestRepository;
import com.hust.exam.service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

@Service
public class StatisticServiceImpl implements StatisticService {

    @Autowired
    ExamRepository examRepository;

    @Autowired
    TestRepository testRepository;

    @Autowired
    TestQuestionRelationRepository testQuestionRelationRepository;

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
            if(studentNum != 0) {
                records.add(new StatisticRecord(i, studentNum));
            }
        }
        return records;
    }

    @Override
    public List<StatisticRecord> getDataByQuesAnswer(int examId) {
        List<Integer> questionIds = new HashSet<>(testRepository.findQuestionIdByExam(examId)).stream().toList();
        System.out.println(questionIds);
        List<StatisticRecord> records = new LinkedList<>();
        for(int i = 0; i < questionIds.size(); i++) {
            int correctNum = testQuestionRelationRepository.findStudentNumByQuesId(questionIds.get(i), true);
            int wrongNum = testQuestionRelationRepository.findStudentNumByQuesId(questionIds.get(i), false);
            records.add(new StatisticRecord(correctNum, wrongNum, questionIds.get(i)));
        }
        return records;
    }

}
