package com.hust.exam.service;


import com.hust.exam.DTO.StatisticRecord;

import java.util.List;

public interface StatisticService {
    List<StatisticRecord> getDataByExam(int examId);
    List<StatisticRecord> getDataByQuesAnswer(int examId);

    List<StatisticRecord> getStudentInfor(int examId);

    Float getAverageScore(int examId);
}
