package com.hust.exam.DTO;

import com.hust.exam.enumobject.Grade;
import com.hust.exam.enumobject.QuestionType;
import com.hust.exam.enumobject.Subject;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class QuestionDto {

    private int id;

    private String content;

    private Grade grade;

    private Subject subject;

    private QuestionType questionType;

    private List<AnswerDto> answers;

    private String correctAnswers;

    private int exam_times;

}
