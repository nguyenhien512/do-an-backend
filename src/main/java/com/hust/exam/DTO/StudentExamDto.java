package com.hust.exam.DTO;

import com.hust.exam.enumobject.ExamStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StudentExamDto {
    private int id;

    private String name;

    private long maxDuration;

    private LocalDateTime openTime;

    private LocalDateTime closeTime;

    private String studentClassName;

    private ExamStatus status;

}
