package com.hust.exam.mapper;

import com.hust.exam.DTO.ClassDto;
import com.hust.exam.models.StudentClass;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ClassMapper {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    ListMapper listMapper;

    public ClassDto toClassDto(StudentClass studentClass) {
        return modelMapper.map(studentClass, ClassDto.class);
    }

    public List<ClassDto> toClassDtoList(List<StudentClass> classes) {
        return listMapper.mapList(classes, ClassDto.class,this::toClassDto);
    }

    public StudentClass toEntity(ClassDto classDto) {
        return modelMapper.map(classDto, StudentClass.class);
    }
}
