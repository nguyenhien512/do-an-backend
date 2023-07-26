package com.hust.exam.repository;

import com.hust.exam.models.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuestionRepository extends JpaRepository<Question,Integer> {

    @Override
    List<Question> findAll();

    @Override
    long count();

    @Override
    Page<Question> findAll(Pageable pageable);

    @Query(value = "select * from questions as q\n" +
            "inner join exam_question_relation as eqr\n" +
            "on q.id = eqr.question_id\n" +
            "where q.id = :id", nativeQuery = true)
    Optional<Question> findQuestionInExam(@Param("id") int questionId);
}
