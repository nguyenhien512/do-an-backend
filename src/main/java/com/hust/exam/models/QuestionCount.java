package com.hust.exam.models;

import com.hust.exam.enumobject.QuestionLevel;

public interface QuestionCount {

    QuestionLevel getLevel();

    Topic getTopic();

    int getNumberOfQuestions();

}
