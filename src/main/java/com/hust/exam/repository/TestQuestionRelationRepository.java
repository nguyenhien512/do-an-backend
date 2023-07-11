package com.hust.exam.repository;


import com.hust.exam.enumobject.AnswerType;
import com.hust.exam.models.TestQuestionRelation;
import com.hust.exam.models.TestQuestionRelationId;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface TestQuestionRelationRepository extends CrudRepository<TestQuestionRelation, TestQuestionRelationId> {

}
