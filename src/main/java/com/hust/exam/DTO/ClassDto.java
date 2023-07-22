package com.hust.exam.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ClassDto {

    private int id;

    private String name;

    private int schoolYear;

    private Set<StudentDto> students;

    private TeacherDto createBy;
}
