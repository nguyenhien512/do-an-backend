package com.hust.exam.mapper;

import com.hust.exam.DTO.TestDto;
import com.hust.exam.DTO.TestResultDto;
import com.hust.exam.models.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TestMapper {
    @Autowired
    ModelMapper modelMapper;

    @Autowired
    ListMapper listMapper;

    public TestDto toTestDto (Test test) {
        return modelMapper.map(test, TestDto.class);
    }

    public List<TestDto> toTestDtoList (List<Test> tests) {
        return listMapper.mapList(tests, TestDto.class, this::toTestDto);
    }

    public TestResultDto toTestResultDto (Test test) {
        return modelMapper.map(test, TestResultDto.class);
    }

    public List<TestResultDto> toTestResultDtoList (List<Test> tests) {
        return listMapper.mapList(tests, TestResultDto.class, this::toTestResultDto);
    }

}
