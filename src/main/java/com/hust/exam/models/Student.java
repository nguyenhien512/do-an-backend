package com.hust.exam.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@DiscriminatorValue("STUDENT")
public class Student extends SystemUser {

    @OneToMany(mappedBy = "student")
    private List<Test> tests;

    @ManyToMany(mappedBy = "students")
    private List<StudentClass> classes;

    public Student(String username, String password, String firstName, String lastName, LocalDate dob, Boolean isActive) {
       super(username, password, firstName, lastName, dob, isActive);
       this.setAuthority("STUDENT");
    }

}
