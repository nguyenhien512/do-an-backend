package com.hust.exam.DTO;

import com.hust.exam.enumobject.Grade;
import com.hust.exam.enumobject.QuestionType;
import com.hust.exam.enumobject.Subject;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class QuestionDto {

    private int id;

    private String content;

    private Grade grade = Grade.GRADE_10;

    private Subject subject = Subject.ENG;

    private QuestionType questionType = QuestionType.SINGLE_CHOICE;

    private List<AnswerDto> answers;

    private String correctAnswers;

    private int exam_times;

}
