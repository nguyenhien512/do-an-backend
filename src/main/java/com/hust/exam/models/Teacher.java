package com.hust.exam.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@DiscriminatorValue("TEACHER")
public class Teacher extends SystemUser{

    @OneToMany(mappedBy = "createBy")
    private List<StudentClass> ownClasses;

    @OneToMany(mappedBy = "createBy")
    private List<Exam> ownExams;

    public Teacher(String username, String password, String firstName, String lastName) {
        super(username, password, firstName, lastName);
        this.setAuthority("TEACHER");
    }

}
