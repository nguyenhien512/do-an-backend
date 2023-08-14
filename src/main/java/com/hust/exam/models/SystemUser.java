package com.hust.exam.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="system_users")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="authority", discriminatorType = DiscriminatorType.STRING)
public class SystemUser implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="username")
    private String username;

    @Column(name="password")
    private String password;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="authority", insertable = false, updatable = false)
    private String authority;

    @Column(name="is_active")
    private boolean isActive;

    @Column(name="dob")
    private LocalDate dob;

    public SystemUser(String username, String password, String firstName, String lastName, Boolean isActive) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.isActive = isActive;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SystemUser user)) return false;
        return Objects.equals(username, user.username) && Objects.equals(password, user.password) && Objects.equals(firstName, user.firstName) && Objects.equals(lastName, user.lastName) && Objects.equals(authority, user.authority);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, firstName, lastName, authority);
    }
}
