package com.hust.exam.models;

import com.hust.exam.enumobject.Grade;
import com.hust.exam.enumobject.Subject;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="matrix")
@Getter
@Setter
@NoArgsConstructor
public class Matrix {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="subject")
    @Enumerated(EnumType.STRING)
    private Subject subject;

    @Column(name="grade")
    @Enumerated(EnumType.STRING)
    private Grade grade;

    @OneToMany(mappedBy = "matrix")
    private Set<MatrixItem> matrixItems;

    @OneToOne(mappedBy = "matrix")
    private Exam exam;

}
