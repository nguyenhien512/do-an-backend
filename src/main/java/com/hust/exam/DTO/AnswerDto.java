package com.hust.exam.DTO;

import com.hust.exam.enumobject.AnswerKey;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AnswerDto {

    private AnswerKey key;

    private String content;

}
