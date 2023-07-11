package com.hust.exam.mapper;

import com.hust.exam.DTO.QuestionDTO;
import com.hust.exam.models.Question;

import java.util.*;

public class QuestionMapper {

    public static QuestionDTO questionToquestionDTO(Question question) {
        QuestionDTO questionDTO = new QuestionDTO();
        questionDTO.setId(question.getId());
        questionDTO.setDescription(question.getDescription());
        questionDTO.setAnswerA(question.getAnswerA());
        questionDTO.setAnswerB(question.getAnswerB());
        questionDTO.setAnswerC(question.getAnswerC());
        questionDTO.setAnswerD(question.getAnswerD());
        return questionDTO;
    }

    public static List<QuestionDTO> questionsToQuestionDTOs(List<Question> questions) {
        List<QuestionDTO> questionDTOs= new ArrayList<>();
        for (Question model : questions) {
            questionDTOs.add(questionToquestionDTO(model));
        }
        return questionDTOs;
    }
}
