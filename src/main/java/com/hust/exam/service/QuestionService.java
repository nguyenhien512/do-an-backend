package com.hust.exam.service;

import com.hust.exam.DTO.AnswerDto;
import com.hust.exam.DTO.QuestionDto;
import com.hust.exam.mapper.AnswerMapper;
import com.hust.exam.mapper.QuestionMapper;
import com.hust.exam.models.Answer;
import com.hust.exam.models.Question;
import com.hust.exam.repository.AnswerRepository;
import com.hust.exam.repository.QuestionRepository;
import com.hust.exam.utils.RandomHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    AnswerRepository answerRepository;

    @Autowired
    QuestionMapper questionMapper;

    @Autowired
    AnswerMapper answerMapper;

    public List<QuestionDto> getAll() {
        List<Question> questions = questionRepository.findAll();
        return questionMapper.toQuestionDtoList(questions);
    }

    public List<QuestionDto> getRandomQuestions(int size) {
        List<Question> result = new ArrayList<>();
        int total = (int) questionRepository.count();
        List<Integer> randomIndexes = RandomHelper.randomIndexes(total,size);
        for (int idx : randomIndexes) {
            Page<Question> questionPage = questionRepository.findAll(PageRequest.of(idx, 1));
            if (questionPage.hasContent()) {
                Question q = questionPage.getContent().get(0);
                result.add(q);
            }
        }
        return questionMapper.toQuestionDtoList(result);
    }

    public QuestionDto findById(int id){
        Question question = questionRepository.findById(id).orElseThrow(() -> new RuntimeException("Không tìm thấy câu hỏi id: "+id));
        return questionMapper.toQuestionDto(question);
    }

    public QuestionDto createQuestion(QuestionDto dto){
        Question entity = questionMapper.toQuestionEntity(dto);
        //return entity;
        entity.setExamTimes(0);
        Question question = addAnswerListToDb(questionRepository.save(entity), dto.getAnswers());
        return questionMapper.toQuestionDto(question);
    }

    public QuestionDto editQuestion(QuestionDto dto) {
        Question entity = questionRepository.findById(dto.getId()).orElseThrow(() -> new RuntimeException("Không tìm thấy câu hỏi id: "+dto.getId()));
        dto.setExam_times(entity.getExamTimes());
        if(entity.getExamTimes() > 0) {
            throw new RuntimeException("Không thể sửa câu hỏi đã từng có trong đề thi!");
        }
        if(entity.equals(questionRepository.findQuestionInExam(dto.getId()).orElse(null))) {
            throw new RuntimeException("Không thể sửa câu hỏi đã từng có trong đề thi!");
        }
        entity = questionMapper.toQuestionEntity(dto);
        Question saved = questionRepository.save(addAnswerListToDb(entity, dto.getAnswers()));
        return questionMapper.toQuestionDto(saved);
    }

    public void deleteQuestion(int id) {
        Question entity = questionRepository.findById(id).orElseThrow(() -> new RuntimeException("Không tìm thấy câu hỏi id: "+id));
        if(entity.getExamTimes() > 0) {
            throw new RuntimeException("Không thể xóa câu hỏi đã từng có trong đề thi!");
        }
        if(entity.equals(questionRepository.findQuestionInExam(id).orElse(null))) {
            throw new RuntimeException("Không thể xóa câu hỏi đã từng có trong đề thi!");
        }
        List<Answer> answerList = answerRepository.findByQuestion(entity);
        if(answerList.size() > 0) {
            answerRepository.deleteAll(answerList);
        }
        questionRepository.delete(entity);
    }

    public Question addAnswerListToDb(Question question, List<AnswerDto> dtos) {
        List<Answer> list = new ArrayList<>();
        if(question.getId() >= 0 && !dtos.isEmpty()) {
            dtos.forEach(answerDto -> {
                if(answerDto.getId() == 0) {
                    Answer answer = answerMapper.toAnswerEntity(answerDto);
                    answer.setQuestion(question);
                    answerRepository.save(answer);
                    list.add(answer);
                }
                else{
                    Answer answer = updateAnswer(answerDto);
                    list.add(answer);
                }
            });
        }
        question.setAnswers(list);
        return question;
    }

    public Answer updateAnswer(AnswerDto answerDto) {
        Answer answer = answerRepository.findById(answerDto.getId()).orElseThrow(() -> new RuntimeException("Không tìm thấy câu trả lời với id: "+answerDto.getId()));
        answer.setKey(answerDto.getKey());
        answer.setContent(answerDto.getContent());
        return answerRepository.save(answer);
    }

    public List<QuestionDto> getQuestionsByExam (int examId) {
        return questionMapper.toQuestionDtoList(questionRepository.findByExam(examId));
    }

}
