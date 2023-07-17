package com.hust.exam.models;

import com.hust.exam.enumobject.ExamStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
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

    @Column(name="max_retry")
    private int maxRetry;

    @ManyToOne
    @JoinColumn(name = "class_id")
    private StudentClass studentClass;

    @ManyToOne
    @JoinColumn(name = "create_by")
    private Teacher createBy;

    @OneToMany(mappedBy = "exam")
    private List<Test> tests;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "exam_question_relation",
            joinColumns = { @JoinColumn(name = "exam_id") },
            inverseJoinColumns = { @JoinColumn(name = "question_id") }
    )
    private List<Question> questions;


}
