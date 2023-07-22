package com.hust.exam.service;

import com.hust.exam.DTO.ClassDto;
import com.hust.exam.mapper.ClassMapper;
import com.hust.exam.models.*;
import com.hust.exam.repository.ClassRepository;
import com.hust.exam.repository.ExamRepository;
import com.hust.exam.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassService {

    @Autowired
    ClassRepository classRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ClassMapper classMapper;

    @Autowired
    ExamRepository examRepository;

    public List<ClassDto> findByCreateBy (String createByUsername) {
        Teacher foundTeacher = (Teacher) userRepository.findById(createByUsername).orElseThrow();
        List<StudentClass> classes = classRepository.findByCreateBy(foundTeacher);
        return classMapper.toClassDtoList(classes);
    }

    public ClassDto findById (int classId) {
        StudentClass found = classRepository.findById(classId).orElseThrow();
        return classMapper.toClassDto(found);
    }

    public ClassDto createClass (String createByUsername, ClassDto classDto) {
        Teacher foundTeacher = (Teacher) userRepository.findById(createByUsername).orElseThrow();
        StudentClass newClass = classMapper.toEntity(classDto);
        newClass.setCreateBy(foundTeacher);
        StudentClass saved = classRepository.save(newClass);
        return classMapper.toClassDto(saved);
    }

    public ClassDto addStudent (int classId, String studentUsername) {
        StudentClass foundClass = classRepository.findById(classId).orElseThrow();
        Student foundStudent = (Student) userRepository.findById(studentUsername).orElseThrow();
        foundClass.getStudents().add(foundStudent);
        StudentClass updated = classRepository.save(foundClass);
        return classMapper.toClassDto(updated);
    }

    public ClassDto removeStudent (int classId, String studentUsername) {
        StudentClass foundClass = classRepository.findById(classId).orElseThrow();
        Student foundStudent = (Student) userRepository.findById(studentUsername).orElseThrow();
        foundClass.getStudents().remove(foundStudent);
        StudentClass saved = classRepository.save(foundClass);
        return classMapper.toClassDto(saved);
    }

    public void deleteClass (int classId) {
        StudentClass foundClass = classRepository.findById(classId).orElseThrow(() -> new RuntimeException("Không tìm thấy lớp id: "+classId));
        List<Exam> examList = examRepository.findByStudentClass(foundClass);
        if(examList.size() > 0) {
            examList.forEach(exam -> exam.setStudentClass(null));
        }
        examRepository.saveAll(examList);
        foundClass.getStudents().clear();
        classRepository.delete(foundClass);
    }

}
