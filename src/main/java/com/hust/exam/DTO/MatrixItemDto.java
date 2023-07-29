package com.hust.exam.DTO;

import com.hust.exam.enumobject.QuestionLevel;
import com.hust.exam.models.Topic;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MatrixItemDto {

    private int id;

    private QuestionLevel level;

    private Topic topic;

    private int numberOfQuestions;
}
