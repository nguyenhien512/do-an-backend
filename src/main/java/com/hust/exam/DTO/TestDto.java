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

    private String studentFirstName;

    private String studentLastName;

    private String studentUsername;

    private int examId;

    private List<TestQuestionRelationDto> testQuestionRelations;

    private int score;

    private LocalDateTime submitTime;

    private LocalDateTime createTime;

    private boolean hasSubmit;

}
