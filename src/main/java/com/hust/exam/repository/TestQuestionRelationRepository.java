package com.hust.exam.repository;


import com.hust.exam.models.TestQuestionRelation;
import com.hust.exam.models.TestQuestionRelationId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestQuestionRelationRepository extends CrudRepository<TestQuestionRelation, TestQuestionRelationId> {

}
