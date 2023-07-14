package com.hust.exam.models;

import com.hust.exam.enumobject.AnswerKey;
import javax.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    @MapsId("questionId")
    private Question question;

    @Column(name="answer_key")
    @Enumerated(EnumType.STRING)
    private AnswerKey key;

    @Column(name="content")
    private String content;

}
