package com.hust.exam.DTO;


import com.hust.exam.enumobject.ExamStatus;
import com.hust.exam.models.StudentClass;
import com.hust.exam.models.Teacher;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TeacherExamDto {

    private int id;

    private String name;

    private int examTimes;

    private long maxDuration;

    private LocalDateTime openTime;

    private LocalDateTime closeTime;

    private ExamStatus examStatus;

    private int maxRetry;

    private StudentClass studentClass;

    private Teacher createByUsername;

}
