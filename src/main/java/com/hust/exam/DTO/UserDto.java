package com.hust.exam.DTO;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDto {

    private String username;

    private String firstName;

    private String password;

    private String lastName;

    private String authority;

    private boolean isActive;

    @Override
    public String toString() {
        return "UserDto{" +
                "username='" + username + '\'' +
                ", firstName='" + firstName + '\'' +
                ", password='" + password + '\'' +
                ", lastName='" + lastName + '\'' +
                ", authority='" + authority + '\'' +
                '}';
    }
}
