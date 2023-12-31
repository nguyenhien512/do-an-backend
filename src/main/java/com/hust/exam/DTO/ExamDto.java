package com.hust.exam.DTO;


import com.hust.exam.enumobject.ExamStatus;
import com.hust.exam.enumobject.Subject;
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
public class ExamDto {

    private int id;

    private String name;

    private int examTimes;

    private long maxDuration;

    private LocalDateTime openTime;

    private LocalDateTime closeTime;

    private ExamStatus status;

    private int maxRetry;

    private int studentClassId;

    private String studentClassName;

    private TeacherDto createBy;

    private Subject subject;

    private int numberOfQuestions;

    private List<QuestionDto> questionDtoList;

}
