package com.hust.exam.models;

import com.hust.exam.enumobject.Grade;
import com.hust.exam.enumobject.QuestionLevel;
import com.hust.exam.enumobject.QuestionStatus;
import com.hust.exam.enumobject.Subject;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="questions")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="content")
    private String content;

    @Column(name="grade")
    @Enumerated(EnumType.STRING)
    private Grade grade;

    @Column(name="subject")
    @Enumerated(EnumType.STRING)
    private Subject subject;

    @Column(name="correct_answers")
    private String correctAnswers;

    @Column(name="exam_times")
    private int examTimes;

    @Column(name="question_level")
    @Enumerated(EnumType.STRING)
    QuestionLevel level;

    @OneToMany(mappedBy = "question")
    private List<Answer> answers = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name="topic_id")
    private Topic topic;

    @ManyToOne
    @JoinColumn(name = "create_by", referencedColumnName = "username")
    private SystemUser createBy;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private QuestionStatus status;

    @Column(name = "comment")
    private String comment;

}
