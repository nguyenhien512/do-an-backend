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
@DiscriminatorValue("STUDENT_ROLE")
public class Student extends SystemUser{

    @OneToMany(mappedBy = "student")
    private List<Test> tests;

}
