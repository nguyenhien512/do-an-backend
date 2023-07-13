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

//    @ManyToMany
//    @JoinTable(name="r_test_question", joinColumns = @JoinColumn(name="test_id"), inverseJoinColumns = @JoinColumn(name="question_id"))
//    private List<Question> questions;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @OneToMany(mappedBy = "test", cascade = CascadeType.ALL)
    private List<TestQuestionRelation> testQuestionRelations;

    @Column(name="score")
    private int score;

    @Column(name="duration")
    private long duration;

    @Column(name="created_date")
    private LocalDateTime createdDate;


    public void addTestQuestionRelation(TestQuestionRelation r) {
        if (testQuestionRelations == null) {
            testQuestionRelations = new ArrayList<>();
        }
        testQuestionRelations.add(r);
    }

    public void score() {
//        int score = 0;
        if (testQuestionRelations != null) {
            for (TestQuestionRelation r : testQuestionRelations) {
                if (r.checkChosenAnswer()) {
                    this.score += 1;
                }
            }
        }
    }

    public void setQuestions(List<Question> questions) {
        for (Question question : questions) {
            TestQuestionRelation relation = new TestQuestionRelation();
            relation.setTest(this);
            relation.setQuestion(question);
            addTestQuestionRelation(relation);
        }
    }
}
