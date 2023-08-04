package com.hust.exam.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Getter
@Setter
@AllArgsConstructor
@Entity
@DiscriminatorValue("ADMIN")
public class Admin extends SystemUser{

    public Admin(String username, String password, String firstName, String lastName, Boolean isActive) {
        super(username, password, firstName, lastName, isActive);
        this.setAuthority("ADMIN");
    }
}
