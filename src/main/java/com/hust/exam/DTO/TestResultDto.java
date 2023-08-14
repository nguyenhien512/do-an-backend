package com.hust.exam.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TestResultDto {

    private int id;

    private int examId;

    private String examName;

    private LocalDateTime examCloseTime;

    private int examExamTimes;

    private StudentDto student;

    private float score;

    private LocalDateTime submitTime;

}
