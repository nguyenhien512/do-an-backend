package com.hust.exam.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @OneToOne
    @JoinColumn(name="mapping_rule_id", referencedColumnName = "id")
    private MappingRule mappingRule;

    @Column(name="question_index")
    private int questionIndex;

}
