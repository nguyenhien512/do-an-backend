package com.hust.exam.DTO;

import com.hust.exam.enumobject.Grade;
import com.hust.exam.enumobject.QuestionLevel;
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

    private Grade grade = Grade.GRADE_10;

    private Subject subject = Subject.ENG;

    private List<AnswerDto> answers;

    private String correctAnswers;

    private int examTimes;

    private QuestionLevel level;

    private TopicDto topic;

    @Override
    public String toString() {
        return "QuestionDto{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", grade=" + grade +
                ", subject=" + subject +
                ", answers=" + answers +
                ", correctAnswers='" + correctAnswers + '\'' +
                ", examTimes=" + examTimes +
                '}';
    }
}
