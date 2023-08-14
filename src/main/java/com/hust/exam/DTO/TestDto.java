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

    private int examId;

    private String examName;

    private List<TestQuestionRelationDto> testQuestionRelations;

    private float score;

    private LocalDateTime submitTime;

    private LocalDateTime createTime;

    private boolean hasSubmit;

}
