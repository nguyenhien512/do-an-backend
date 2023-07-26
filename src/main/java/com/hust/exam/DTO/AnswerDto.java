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

    private int id = 0;

    private AnswerKey key = AnswerKey.A;

    private String content;

    @Override
    public String toString() {
        return "AnswerDto{" +
                "id=" + id +
                ", key=" + key +
                ", content='" + content + '\'' +
                '}';
    }
}
