package com.hust.exam.models;

import com.hust.exam.enumobject.AnswerType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

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

    @Column(name="description")
    private String description;

    @Column(name="answer_a")
    private String answerA;

    @Column(name="answer_b")
    private String answerB;

    @Column(name="answer_c")
    private String answerC;

    @Column(name="answer_d")
    private String answerD;

    @Column(name="correct_answer", updatable = false)
    @Enumerated(EnumType.STRING)
    private AnswerType correctAnswer;

    public boolean checkAnswer(AnswerType chosenAnswer) {
        return chosenAnswer == correctAnswer;
    }
}
