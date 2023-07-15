package com.hust.exam.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    private List<TestQuestionRelation> testQuestionRelations = new ArrayList<>();

    @Column(name="score")
    private int score;

    @Column(name="duration")
    private long duration;

    @Column(name="create_time")
    private LocalDateTime createTime;

    @Column(name="has_submit")
    private boolean hasSubmit;

    public void addQuestion (Question question, int questionIndex, MappingRule mappingRule) {
        TestQuestionRelation relation = new TestQuestionRelation();
        relation.setTest(this);
        relation.setQuestion(question);
        relation.setQuestionIndex(questionIndex);
        relation.setMappingRule(mappingRule);
        testQuestionRelations.add(relation);
    }

    @Transient
    private List<Question> questions = testQuestionRelations
            .stream()
            .map(TestQuestionRelation::getQuestion)
            .collect(Collectors.toList());

}
