package com.hust.exam.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="exam_question_relation")
@Getter
@Setter
@NoArgsConstructor
public class ExamQuestionRelation {

    @EmbeddedId
    private ExamQuestionRelationId examQuestionRelationId = new ExamQuestionRelationId();

    @ManyToOne
    @MapsId("examId")
    private Exam exam;

    @ManyToOne
    @MapsId("questionId")
    private Question question;

    @Column(name="question_index")
    private int questionIndex;

}
