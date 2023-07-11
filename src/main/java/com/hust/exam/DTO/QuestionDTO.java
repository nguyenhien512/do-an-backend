package com.hust.exam.DTO;

import com.hust.exam.enumobject.AnswerType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class QuestionDTO {

    private int id;

    private String description;

    private String answerA;

    private String answerB;

    private String answerC;

    private String answerD;

}
