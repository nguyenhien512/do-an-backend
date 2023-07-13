package com.hust.exam.models;

import com.hust.exam.enumobject.AnswerType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="r_test_question")
@Getter
@Setter
@NoArgsConstructor
public class TestQuestionRelation {

    @EmbeddedId
    private TestQuestionRelationId testQuestionRelationId = new TestQuestionRelationId();

    @ManyToOne
    @MapsId("testId")
    private Test test;

    @ManyToOne
    @MapsId("questionId")
    private Question question;

    @Column(name="chosen_answer")
    @Enumerated(EnumType.STRING)
    private AnswerType chosenAnswer;

    public boolean checkChosenAnswer() {
        return question.checkAnswer(chosenAnswer);
    }

}
