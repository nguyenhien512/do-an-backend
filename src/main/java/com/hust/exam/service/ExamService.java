package com.hust.exam.service;

import com.hust.exam.DTO.ExamCountDto;
import com.hust.exam.DTO.ExamDto;
import com.hust.exam.DTO.QuestionCountDto;
import com.hust.exam.enumobject.ExamStatus;
import com.hust.exam.mapper.ExamCountMapper;
import com.hust.exam.mapper.ExamMapper;
import com.hust.exam.mapper.QuestionCountMapper;
import com.hust.exam.models.*;
import com.hust.exam.repository.ClassRepository;
import com.hust.exam.repository.ExamRepository;
import com.hust.exam.repository.QuestionRepository;
import com.hust.exam.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExamService {

    @Autowired
    ExamRepository examRepository;

    @Autowired
    ClassRepository classRepository;

    @Autowired
    ExamMapper examMapper;

    @Autowired
    ExamCountMapper examCountMapper;

    @Autowired
    QuestionCountMapper questionCountMapper;

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    QuestionService questionService;

    public List<ExamDto> findAll (){
        return examMapper.toExamDtoList(examRepository.findAll());
    }
    public List<ExamDto> findPublishedExamsByStudent (String username) {
        return examMapper.toExamDtoList(examRepository.findExamsByStudent(username, ExamStatus.PUBLISHED));
    }

    public List<ExamDto> findByTeacher (String username) {
        return examMapper.toExamDtoList(examRepository.findByTeacherUsername(username));
    }

    public boolean checkExamOpen(Exam exam) {
        LocalDateTime now = LocalDateTime.now();
        return exam.getOpenTime().isBefore(now) && exam.getCloseTime().isAfter(now);
    }

    public List<ExamCountDto> countTestsByClassGroupByStudent(int classId) {
        List<ExamCount> examCounts = examRepository.countTestsByClassGroupByStudent(classId);
        return examCountMapper.toExamCountDtoList(examCounts);
    }

    public ExamDto findExamById(int id) {
        Exam exam = examRepository.findById(id).orElseThrow(() -> new RuntimeException("Không tìm thấy đề thi với id: "+id));
        return examMapper.toExamDto(exam);
    }

    public ExamDto createExam(String createByUsername, ExamDto dto) {
        Exam exam = examMapper.toExamEntity(dto);
        Teacher teacher = (Teacher) userRepository.findById(createByUsername).orElseThrow();
        exam.setStatus(ExamStatus.UNPUBLISHED);
        exam.setExamTimes(0);
        exam.setCreateBy(teacher);
        StudentClass studentClass = classRepository.findById(dto.getStudentClassId()).orElse(null);
        exam.setStudentClass(studentClass);
        return examMapper.toExamDto(examRepository.save(exam));
    }

    public ExamDto updateExam(ExamDto dto) {
        Exam exam = examRepository.findById(dto.getId()).orElseThrow(() -> new RuntimeException("Không tìm thấy đề thi với id: "+dto.getId()));
//        exam.setStatus(ExamStatus.UNPUBLISHED);
        exam.setOpenTime(dto.getOpenTime());
        exam.setCloseTime(dto.getCloseTime());
        exam.setMaxDuration(dto.getMaxDuration());
        exam.setMaxRetry(dto.getMaxRetry());
        exam.setName(dto.getName());
        StudentClass studentClass = classRepository.findById(dto.getStudentClassId()).orElseThrow(() -> new RuntimeException("Không tìm thấy lớp "+dto.getStudentClassName()));
        exam.setStudentClass(studentClass);
        return examMapper.toExamDto(examRepository.save(exam));
    }

    public ExamDto publishExam(int id) {
        Exam exam = examRepository.findById(id).orElseThrow(() -> new RuntimeException("Không tìm thấy đề thi với id: "+id));
        exam.setStatus(ExamStatus.PUBLISHED);
        return examMapper.toExamDto(examRepository.save(exam));
    }

    public void deleteExam(int id) {
        Exam exam = examRepository.findById(id).orElseThrow(() -> new RuntimeException("Không tìm thấy đề thi với id: "+id));
        if(exam.getStatus().equals(ExamStatus.PUBLISHED)) {
            throw new RuntimeException("Không thể xóa đề thi đã XUẤT BẢN");
        }
        for (Question q : exam.getQuestions()) {
            q.setExamTimes(q.getExamTimes() - 1);
            questionRepository.save(q);
        }
        exam.getQuestions().clear();
        examRepository.delete(exam);
    }

    public ExamDto addQuestionsToExam(int examId, List<Integer> questionDtoIds) {
        Exam exam = examRepository.findById(examId).orElseThrow(() -> new RuntimeException("Không tìm thấy đề thi với id: "+examId));
//        if(exam.getStatus().equals(ExamStatus.PUBLISHED)) {
//            throw new RuntimeException("Không thể chỉnh sửa đề thi đã XUẤT BẢN");
//        }
        List<Question> questions = questionRepository.findByIdIn(questionDtoIds);
        if(exam.getQuestions().size() >= 0) {
            exam.getQuestions().addAll(questions);
            questions.forEach(q -> q.setExamTimes(q.getExamTimes() + 1));
            questionRepository.saveAll(questions);
            questions = exam.getQuestions().stream().distinct().collect(Collectors.toList());
        }
        exam.setQuestions(questions);
        return examMapper.toExamDto(examRepository.save(exam));
    }

    public ExamDto removeQuestionsFromExam(int examId, List<Integer> questionDtoIds) {
        Exam exam = examRepository.findById(examId).orElseThrow(() -> new RuntimeException("Không tìm thấy đề thi với id: "+examId));
//        if(exam.getStatus().equals(ExamStatus.PUBLISHED)) {
//            throw new RuntimeException("Không thể xóa đề thi đã XUẤT BẢN");
//        }
        List<Question> questions = questionRepository.findByIdIn(questionDtoIds);
        exam.getQuestions().removeAll(questions);
        questions.forEach(q -> q.setExamTimes(q.getExamTimes() - 1));
        questionRepository.saveAll(questions);
        return examMapper.toExamDto(examRepository.save(exam));
    }

    public List<QuestionCountDto> calculateMatrix(int examId) {
        List<QuestionCount> counts = examRepository.countByMatrix(examId);
        return questionCountMapper.toQuestionCountDtoList(counts);
    }

    public ExamDto setQuestionsByMatrix(int examId, List<QuestionCountDto> matrix) {
        Exam exam = examRepository.findById(examId).orElseThrow(() -> new RuntimeException("Không tìm thấy đề thi với id: "+examId));
        List<Question> questions = questionService.findQuestionsByMatrix(matrix);
        exam.setQuestions(questions);
        for (Question q : questions) {
            q.setExamTimes(q.getExamTimes() + 1);
        }
        questionRepository.saveAll(questions);
        Exam saved = examRepository.save(exam);
        return examMapper.toExamDto(saved);
    }
}
