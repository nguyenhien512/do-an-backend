package com.hust.exam.mapper;

import com.hust.exam.DTO.ClassDto;
import com.hust.exam.models.StudentClass;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
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
        TypeMap<ClassDto, StudentClass> propertyMapper = modelMapper.getTypeMap(ClassDto.class, StudentClass.class);
        if (propertyMapper == null) {
            propertyMapper = modelMapper.createTypeMap(ClassDto.class, StudentClass.class);
        }
        propertyMapper.addMappings(mapper -> {
            mapper.skip(StudentClass::setCreateBy);
            mapper.skip(StudentClass::setId);
            mapper.skip(StudentClass::setStudents);
        });
        return modelMapper.map(classDto, StudentClass.class);
    }
}
