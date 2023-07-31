package com.hust.exam.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Table(name="test_question_relation")
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

    @Column(name="answers")
    private String answers;

    @Column(name="question_index")
    private int questionIndex;

    @Column(name="is_correct")
    private boolean isCorrect;

    @ManyToOne
    @JoinColumn(name="mapping_rule_id")
    private MappingRule mappingRule;

}
