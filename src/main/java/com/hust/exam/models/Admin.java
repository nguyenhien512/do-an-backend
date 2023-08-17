package com.hust.exam.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@Entity
@DiscriminatorValue("ADMIN")
public class Admin extends SystemUser{

    public Admin(String username, String password, String firstName, String lastName, LocalDate dob, Boolean isActive) {
        super(username, password, firstName, lastName, dob, isActive);
        this.setAuthority("ADMIN");
    }
}
