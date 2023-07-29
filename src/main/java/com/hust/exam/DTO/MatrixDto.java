package com.hust.exam.DTO;

import com.hust.exam.enumobject.Grade;
import com.hust.exam.enumobject.Subject;
import com.hust.exam.models.MatrixItem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MatrixDto {

    private int id;

    private Subject subject;

    private Grade grade;

    private Map<MatrixItem, Integer> matrixMap;
}
