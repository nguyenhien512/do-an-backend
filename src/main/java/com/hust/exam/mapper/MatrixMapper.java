package com.hust.exam.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class MatrixMapper {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    ListMapper listMapper;

}
