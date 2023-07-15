package com.hust.exam.DTO;

import com.hust.exam.models.Exam;
import com.hust.exam.models.Student;
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

    private Student student;

    private Exam exam;

    private int score;

    private long duration;

    private LocalDateTime createTime;

    private boolean hasSubmit;
}
