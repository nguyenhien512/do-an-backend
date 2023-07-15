package com.hust.exam.models;

import javax.persistence.*;
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
