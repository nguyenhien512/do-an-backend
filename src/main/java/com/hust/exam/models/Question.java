package com.hust.exam.models;

import com.hust.exam.enumobject.Grade;
import com.hust.exam.enumobject.QuestionType;
import com.hust.exam.enumobject.Subject;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="questions")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="content")
    private String content;

    @Column(name="grade")
    @Enumerated(EnumType.STRING)
    private Grade grade;

    @Column(name="subject")
    @Enumerated(EnumType.STRING)
    private Subject subject;

    @Column(name="question_type")
    @Enumerated(EnumType.STRING)
    private QuestionType questionType;

    @Column(name="correct_answers")
    private String correctAnswers;

    @Column(name="exam_times")
    private int examTimes;

    @OneToMany(mappedBy = "question")
    private List<Answer> answers = new ArrayList<>();

    public void addAnswer(Answer answer) {
        answers.add(answer);
    }

}
