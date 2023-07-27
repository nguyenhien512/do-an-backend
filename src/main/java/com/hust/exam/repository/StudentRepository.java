package com.hust.exam.repository;

import com.hust.exam.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Integer> {

    @Query("Select s from Student s where concat(firstName,' ',lastName) like %?1% or concat(lastName,' ',firstName) like %?1% or username like %?1%")
    List<Student> searchByNameOrUsername (String queryString);
}
