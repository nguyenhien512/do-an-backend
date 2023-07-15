package com.hust.exam.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Embeddable
@Data
public class TestQuestionRelationId implements Serializable {

    @Column(name="test_id")
    private int testId;

    @Column(name="question_id")
    private int questionId;
}
