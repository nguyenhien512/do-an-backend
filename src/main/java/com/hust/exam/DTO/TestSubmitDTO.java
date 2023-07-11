package com.hust.exam.DTO;

import com.hust.exam.enumobject.AnswerType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TestSubmitDTO {

    private int duration;

    private Map<Integer, AnswerType> answers;
}
