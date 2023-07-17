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

    private String studentFirstName;

    private String studentLastName;

    private int examName;

    private int score;

    private LocalDateTime submitTime;

    private boolean hasSubmit;
}
