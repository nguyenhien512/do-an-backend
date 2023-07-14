package com.hust.exam.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
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
public class Student extends SystemUser{

    @OneToMany(mappedBy = "student")
    private List<Test> tests;

    @ManyToMany(mappedBy = "students")
    private List<StudentClass> classes;

}
