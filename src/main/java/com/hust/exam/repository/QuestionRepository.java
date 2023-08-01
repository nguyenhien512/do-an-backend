package com.hust.exam.repository;

import com.hust.exam.enumobject.QuestionLevel;
import com.hust.exam.models.Question;
import com.hust.exam.models.Topic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface QuestionRepository extends JpaRepository<Question,Integer> {

    @Override
    List<Question> findAll();

    long countByLevelAndTopic(QuestionLevel level, Topic topic);

    @Override
    Page<Question> findAll(Pageable pageable);

    @Query(value = "select * from questions as q\n" +
            "inner join exam_question_relation as eqr\n" +
            "on q.id = eqr.question_id\n" +
            "where q.id = :id", nativeQuery = true)
    Optional<Question> findQuestionInExam(@Param("id") int questionId);

    @Query(value = "SELECT * from questions AS q INNER JOIN exam_question_relation AS r ON q.id = r.question_id WHERE r.exam_id = ?1 ", nativeQuery = true)
    List<Question> findByExam(int examId);
    List<Question> findByIdIn(List<Integer> ids);

    Page<Question> findByLevelAndTopic(QuestionLevel level, Topic topic, Pageable pageable);

    @Query(value="SELECT q FROM Question q INNER JOIN q.answers a WHERE lower(q.content) LIKE lower(concat('%', ?1,'%')) OR lower(a.content) LIKE lower(concat('%', ?1,'%'))")
    Set<Question> findByContent(String content);
}
