package com.hust.exam.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Embeddable
@Data
public class ExamQuestionRelationId implements Serializable {

    @Column(name="exam_id")
    private int testId;

    @Column(name="question_id")
    private int questionId;
}