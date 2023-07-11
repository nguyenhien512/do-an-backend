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
public class TestDTO {
    private int id;

    private List<QuestionDTO> questions;

    private String student;

    private LocalDateTime createdDate;

}
