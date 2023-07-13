package com.hust.exam.repository;

import com.hust.exam.models.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<Student, String> {
}
