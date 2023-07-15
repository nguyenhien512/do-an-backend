package com.hust.exam.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table (name = "tests")
public class Test {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "exam_id")
    private Exam exam;

    @OneToMany(mappedBy = "test", cascade = CascadeType.ALL)
    private List<TestQuestionRelation> testQuestionRelations;

    @Column(name="score")
    private int score;

    @Column(name="duration")
    private long duration;

    @Column(name="create_time")
    private LocalDateTime createTime;

    @Column(name="has_submit")
    private boolean hasSubmit;

    public void addTestQuestionRelation(TestQuestionRelation r) {
        if (testQuestionRelations == null) {
            testQuestionRelations = new ArrayList<>();
        }
        testQuestionRelations.add(r);
    }

    public void setQuestions(List<Question> questions) {
        for (Question question : questions) {
            TestQuestionRelation relation = new TestQuestionRelation();
            relation.setTest(this);
            relation.setQuestion(question);
            addTestQuestionRelation(relation);
            //need to set question_index for each relation
        }
    }
}
