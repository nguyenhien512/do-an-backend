package com.hust.exam.models;

import com.hust.exam.enumobject.ExamStatus;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "exams")
public class Exam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="name")
    private String name;

    @Column(name="exam_times")
    private int examTimes;

    @Column(name="max_duration")
    private long maxDuration;

    @Column(name="open_time")
    private LocalDateTime openTime;

    @Column(name="close_time")
    private LocalDateTime closeTime;

    @Column(name="exam_status")
    @Enumerated(EnumType.STRING)
    private ExamStatus examStatus;

    @ManyToOne
    @JoinColumn(name = "class_id")
    private StudentClass studentClass;

    @ManyToOne
    @JoinColumn(name = "create_by")
    private Teacher createBy;

    @OneToMany(mappedBy = "exam")
    private List<Test> tests;

    @OneToMany(mappedBy = "exam", cascade = CascadeType.ALL)
    private List<ExamQuestionRelation> examQuestionRelations = new ArrayList<>();

    public void addExamQuestionRelation(ExamQuestionRelation r) {
        examQuestionRelations.add(r);
    }

    public void setQuestions(List<Question> questions) {
        for (Question question : questions) {
            ExamQuestionRelation relation = new ExamQuestionRelation();
            relation.setExam(this);
            relation.setQuestion(question);
            addExamQuestionRelation(relation);
            //need to set question_index for each relation
        }
    }
}
