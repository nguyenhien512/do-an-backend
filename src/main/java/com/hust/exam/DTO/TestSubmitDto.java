package com.hust.exam.DTO;

import com.hust.exam.enumobject.AnswerKey;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TestSubmitDto {

    private int duration;

    private Map<Integer, AnswerKey> answers;
}
