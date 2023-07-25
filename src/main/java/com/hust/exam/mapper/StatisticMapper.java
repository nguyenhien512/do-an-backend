package com.hust.exam.mapper;

import com.hust.exam.DTO.StatisticRecord;
import com.hust.exam.models.statistic.StudentScore;

import java.util.List;
import java.util.stream.Collectors;


public class StatisticMapper {
    static StatisticRecord toDto(StudentScore student) {
        StatisticRecord record = new StatisticRecord();
        record.setScore(student.getScore());
        record.setUsername(student.getUsername());
        record.setFirstName(student.getFirstName());
        record.setLastName(student.getLastName());
        return record;
    }

    public static List<StatisticRecord> toDtoList(List<StudentScore> students) {
        return students.stream().map(StatisticMapper::toDto).collect(Collectors.toList());
    }

}
