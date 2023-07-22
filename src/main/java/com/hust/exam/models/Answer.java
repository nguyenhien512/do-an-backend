package com.hust.exam.models;

import com.hust.exam.enumobject.AnswerKey;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="answers")
@Getter
@Setter
@NoArgsConstructor
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "question_id", referencedColumnName = "id")
    private Question question;

    @Column(name="answer_key")
    @Enumerated(EnumType.STRING)
    private AnswerKey key;

    @Column(name="content")
    private String content;

}
