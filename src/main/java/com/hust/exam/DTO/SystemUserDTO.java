package com.hust.exam.DTO;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SystemUserDTO {
    private String username;

    private String firstName;

    private String password;

    private String lastName;

    private String authorityType;
}
