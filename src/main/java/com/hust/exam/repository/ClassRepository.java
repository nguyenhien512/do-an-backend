package com.hust.exam.repository;

import com.hust.exam.models.StudentClass;
import com.hust.exam.models.Teacher;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassRepository extends CrudRepository<StudentClass, Integer> {

    @Query("SELECT c from StudentClass c WHERE c.createBy = ?1")
    List<StudentClass> findByCreateBy (Teacher createBy);
}
