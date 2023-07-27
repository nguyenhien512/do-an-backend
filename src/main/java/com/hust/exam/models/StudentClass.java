package com.hust.exam.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


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

    @Column(name="name")
    private String name;

    @Column(name="school_year")
    private int schoolYear;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "student_class_relation",
            joinColumns = { @JoinColumn(name = "class_id") },
            inverseJoinColumns = { @JoinColumn(name = "student_id") }
    )
    private Set<Student> students = new HashSet<>();

    @OneToMany(mappedBy = "studentClass")
    private List<Exam> exams;

    @ManyToOne
    @JoinColumn(name = "create_by")
    private Teacher createBy;

}
