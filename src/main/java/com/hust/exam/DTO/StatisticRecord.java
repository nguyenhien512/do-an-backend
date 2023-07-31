package com.hust.exam.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class StatisticRecord {
    private int point;
    private int studentNum;
    private int correct;
    private int wrong;
    private String firstName;
    private String lastName;
    private String username;
    private float score;
    private String level;

    public StatisticRecord(int i, int studentNum) {
        setPoint(i);
        setStudentNum(studentNum);
    }

    public StatisticRecord(int correct, int wrong, int i, String level) {
        setCorrect(correct);
        setWrong(wrong);
        setPoint(i);
        setLevel(level);
    }
}
