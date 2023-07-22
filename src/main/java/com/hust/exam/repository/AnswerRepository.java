package com.hust.exam.repository;

import com.hust.exam.models.Answer;
import com.hust.exam.models.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer,Integer> {
    List<Answer> findByQuestion(Question question);
}
