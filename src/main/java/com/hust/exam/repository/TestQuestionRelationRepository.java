package com.hust.exam.repository;


import com.hust.exam.models.TestQuestionRelation;
import com.hust.exam.models.TestQuestionRelationId;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestQuestionRelationRepository extends CrudRepository<TestQuestionRelation, TestQuestionRelationId> {
    @Query(value = "select count(tq.test_id) from test_question_relation tq where tq.question_id = :questionId and tq.test_id in :testIds and is_correct = :isCorrect", nativeQuery = true)
    int findStudentNumByQuesId(List<Integer> testIds, int questionId, boolean isCorrect);

    @Query(value = "select q.question_level from questions q where q.id = :questionId", nativeQuery = true)
    String findQuestionType(int questionId);
}
