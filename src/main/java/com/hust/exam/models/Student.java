package com.hust.exam.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Student extends SystemUser{

    @OneToMany(mappedBy = "student")
    private List<Test> tests;

    @ManyToMany(mappedBy = "students")
    private List<StudentClass> classes;

}
