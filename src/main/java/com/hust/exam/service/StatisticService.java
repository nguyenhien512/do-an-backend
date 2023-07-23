package com.hust.exam.service;


import com.hust.exam.DTO.StatisticRecord;

import java.util.List;

public interface StatisticService {
    List<StatisticRecord> getDataByExam(int examId);
}
