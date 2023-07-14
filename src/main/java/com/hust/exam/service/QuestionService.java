package com.hust.exam.service;

import com.hust.exam.models.Question;
import com.hust.exam.repository.QuestionRepository;
import com.hust.exam.utils.RandomHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
    @Autowired
    QuestionRepository questionRepository;

    public List<Question> getAll() {
        return questionRepository.findAll();
    }

    public List<Question> getRandomQuestions(int size) {
        List<Question> result = new ArrayList<>();
        int total = (int) questionRepository.count();
        List<Integer> randomIndexes = RandomHelper.randomIndexes(total,size);
        for (int idx : randomIndexes) {
            Page<Question> questionPage = questionRepository.findAll(PageRequest.of(idx, 1));
            if (questionPage.hasContent()) {
                Question q = questionPage.getContent().get(0);
                result.add(q);
            }
        }
        return result;
    }

}
