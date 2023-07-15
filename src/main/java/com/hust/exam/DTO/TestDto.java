package com.hust.exam.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TestDto {

    private int id;

    private StudentDto student;

    private ExamDto exam;

    private List<QuestionDto> questions;

    private int score;

    private long duration;

    private LocalDateTime createTime;

    private boolean hasSubmit;

}
