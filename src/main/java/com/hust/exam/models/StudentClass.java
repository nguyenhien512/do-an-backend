package com.hust.exam.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="classes")
public class StudentClass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "student_class_relation",
            joinColumns = { @JoinColumn(name = "class_id") },
            inverseJoinColumns = { @JoinColumn(name = "student_id") }
    )
    private List<Student> students;

    @OneToMany(mappedBy = "studentClass")
    private List<Exam> exams;

    @ManyToOne
    @JoinColumn(name = "create_by")
    private Teacher createBy;
}
