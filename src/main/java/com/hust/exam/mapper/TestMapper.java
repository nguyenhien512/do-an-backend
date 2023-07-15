package com.hust.exam.mapper;

import com.hust.exam.DTO.TestDto;
import com.hust.exam.DTO.TestResultDto;
import com.hust.exam.models.Test;
import org.modelmapper.ModelMapper;

public class TestMapper {
    static ModelMapper modelMapper = new ModelMapper();
    static {
        modelMapper.getConfiguration().setSkipNullEnabled(true);
    }
    public static TestDto toTestDto (Test test) {
        return modelMapper.map(test, TestDto.class);
    }

    public static TestResultDto toTestResultDto (Test test) {
        return modelMapper.map(test, TestResultDto.class);
    }

}
