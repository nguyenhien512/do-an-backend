package com.hust.exam.repository;

import com.hust.exam.models.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question,Integer> {

    @Override
    List<Question> findAll();

    @Override
    long count();

    @Override
    Page<Question> findAll(Pageable pageable);

    @Query(value = "SELECT * from questions AS q INNER JOIN exam_question_relation AS r ON q.id = r.question_id WHERE r.exam_id = ?1 ", nativeQuery = true)
    List<Question> findByExam(int examId);
}
