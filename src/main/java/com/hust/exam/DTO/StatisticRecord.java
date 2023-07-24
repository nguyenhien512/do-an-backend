package com.hust.exam.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class StatisticRecord {
    private int point;
    private int studentNum;
    private int correct;
    private int wrong;

    public StatisticRecord(int i, int studentNum) {
        setPoint(i);
        setStudentNum(studentNum);
    }

    public StatisticRecord(int correct, int wrong, int i) {
        setCorrect(correct);
        setWrong(wrong);
        setPoint(i);
    }
}
