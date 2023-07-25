package com.hust.exam.repository;

import com.hust.exam.models.Exam;
import com.hust.exam.models.Student;
import com.hust.exam.models.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestRepository extends JpaRepository<Test, Integer> {
    @Query("SELECT t FROM Test t INNER JOIN t.exam e WHERE e.id = ?1 AND t.hasSubmit = true")
    List<Test> findByExam(int examId);


    @Query(value = "SELECT COUNT(*) FROM Test t WHERE t.exam = ?1 AND t.student = ?2")
    int countByExamAndStudent(Exam exam, Student student);

    @Query(value = "select tq.question_id from tests t join test_question_relation tq on t.id = tq.test_id where t.exam_id = :examId", nativeQuery = true)
    List<Integer> findQuestionIdByExam(int examId);


}
