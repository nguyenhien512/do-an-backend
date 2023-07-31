package com.hust.exam.DTO;

import com.hust.exam.enumobject.QuestionLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class QuestionCountDto {
    private QuestionLevel level;

    private TopicDto topic;

    int numberOfQuestions;
}
