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

    public StatisticRecord(int i, int studentNum) {
        setPoint(i);
        setStudentNum(studentNum);
    }

    public StatisticRecord(int correct, int wrong, int i) {
        setCorrect(correct);
        setWrong(wrong);
        setPoint(i);
    }

    public StatisticRecord(String firstName, String lastName, String username, float score) {
        setFirstName(firstName);
        setLastName(lastName);
        setUsername(username);
        setScore(score);
    }
}
