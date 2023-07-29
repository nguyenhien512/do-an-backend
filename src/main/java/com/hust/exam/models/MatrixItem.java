package com.hust.exam.models;

import com.hust.exam.enumobject.QuestionLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="matrix_items")
@Getter
@Setter
@NoArgsConstructor
public class MatrixItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="level")
    private QuestionLevel level;

    @OneToOne
    @JoinColumn(name="topic_id", referencedColumnName = "id")
    private Topic topic;

    @Column(name="number_of_questions")
    private int numberOfQuestions;

    @ManyToOne
    @JoinColumn(name = "matrix_id")
    private Matrix matrix;
}
