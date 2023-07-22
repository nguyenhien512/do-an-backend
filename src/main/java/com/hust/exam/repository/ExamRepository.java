package com.hust.exam.repository;

import com.hust.exam.enumobject.ExamStatus;
import com.hust.exam.models.Exam;
import com.hust.exam.models.StudentClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExamRepository  extends JpaRepository<Exam,Integer> {

    @Query("SELECT e FROM Exam e INNER JOIN e.studentClass c INNER JOIN c.students s WHERE s.username = ?1 AND e.status = ?2")
    List<Exam> findExamsByStudent(String username, ExamStatus examStatus);

    @Query("SELECT e FROM Exam e INNER JOIN e.createBy t WHERE t.username = ?1")
    List<Exam> findByTeacherUsername(String username);

    List<Exam> findByStudentClass(StudentClass studentClass);
}
