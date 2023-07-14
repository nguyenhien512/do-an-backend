package com.hust.exam.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Teacher extends SystemUser{

    @OneToMany(mappedBy = "createBy")
    private List<StudentClass> ownClasses;

    @OneToMany(mappedBy = "createBy")
    private List<Exam> ownExams;
}