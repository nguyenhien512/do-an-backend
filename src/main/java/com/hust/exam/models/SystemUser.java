package com.hust.exam.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="system_users")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)

public class SystemUser {
    @Id
    @Column(name="username")
    private String username;

    private String password;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

}
