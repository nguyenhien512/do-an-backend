package com.hust.exam.service;

import com.hust.exam.DTO.AnswerDto;
import com.hust.exam.DTO.QuestionCountDto;
import com.hust.exam.DTO.QuestionDto;
import com.hust.exam.enumobject.QuestionLevel;
import com.hust.exam.enumobject.QuestionStatus;
import com.hust.exam.mapper.AnswerMapper;
import com.hust.exam.mapper.QuestionMapper;
import com.hust.exam.models.Answer;
import com.hust.exam.models.Question;
import com.hust.exam.models.SystemUser;
import com.hust.exam.models.Topic;
import com.hust.exam.repository.AnswerRepository;
import com.hust.exam.repository.QuestionRepository;
import com.hust.exam.repository.TopicRepository;
import com.hust.exam.repository.UserRepository;
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

    @Autowired
    TopicRepository topicRepository;

    @Autowired
    UserRepository userRepository;

    public List<QuestionDto> getAll() {
        List<Question> questions = questionRepository.findAll();
        return questionMapper.toQuestionDtoList(questions);
    }

    public QuestionDto findById(int id){
        Question question = questionRepository.findById(id).orElseThrow(() -> new RuntimeException("Không tìm thấy câu hỏi id: "+id));
        return questionMapper.toQuestionDto(question);
    }

    public QuestionDto createQuestion(String createByUsername, QuestionDto dto){
        SystemUser createBy = userRepository.findById(createByUsername).get();
        Question entity = questionMapper.toQuestionEntity(dto);
        //return entity;
        entity.setExamTimes(0);
        entity.setCreateBy(createBy);
        entity.setStatus(QuestionStatus.PENDING);
        Topic topic = topicRepository.findById(dto.getTopic().getId()).orElseThrow();
        entity.setTopic(topic);
        Question saved = addAnswerListToDb(questionRepository.save(entity), dto.getAnswers());
        return questionMapper.toQuestionDto(saved);
    }

    public QuestionDto editQuestion(String requestUsername, QuestionDto dto) {
        Question entity = questionRepository.findById(dto.getId()).orElseThrow(() -> new RuntimeException("Không tìm thấy câu hỏi id: "+dto.getId()));
        if (!entity.getCreateBy().getUsername().equals(requestUsername)) {
            System.out.println(entity.getCreateBy().getUsername());
            System.out.println(requestUsername);
            throw new RuntimeException("Chỉ người tạo mới có quyền chỉnh sửa câu hỏi");
        }
        if(entity.getExamTimes() > 0) {
            throw new RuntimeException("Không thể sửa câu hỏi đã từng có trong đề thi!");
        }
        if(entity.equals(questionRepository.findQuestionInExam(dto.getId()).orElse(null))) {
            throw new RuntimeException("Không thể sửa câu hỏi đã từng có trong đề thi!");
        }
        Question notPersisted = questionMapper.toQuestionEntity(dto);
        notPersisted.setStatus(QuestionStatus.PENDING);
        notPersisted.setExamTimes(entity.getExamTimes());
        notPersisted.setCreateBy(entity.getCreateBy());
        questionRepository.save(addAnswerListToDb(notPersisted, dto.getAnswers()));
        Question recall = questionRepository.findById(dto.getId()).orElseThrow();
        return questionMapper.toQuestionDto(recall);
    }

    private void delete(int id) {
        System.out.println("Id ="+id);
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

    public void deleteQuestion(List<Integer> qIds) {
        for (Integer id : qIds) {
            delete(id);
        }
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

    public List<QuestionDto> searchByContent(String content, List<QuestionStatus> statuses) {
        List<Question> questions = new ArrayList<>(questionRepository.findByContent(content, statuses));
        return questionMapper.toQuestionDtoList(questions);
    }


    private List<Question> getRandomQuestions(QuestionLevel level, Topic topic, int size, QuestionStatus status) {
        List<Question> result = new ArrayList<>();
        int total = (int) questionRepository.countByLevelAndTopicAndStatus(level, topic, status);
        List<Integer> randomIndexes = RandomHelper.randomIndexes(total,size);
        if (size > total) {
            throw new RuntimeException(String.format("Ngân hàng đề thi không đủ câu hỏi với mức độ %s, chủ đề %s", level.getText(), topic.getName()));
        }
        for (int idx : randomIndexes) {
            Page<Question> questionPage = questionRepository.findByLevelAndTopicAndStatus(level, topic, status, PageRequest.of(idx, 1));
            if (questionPage.hasContent()) {
                Question q = questionPage.getContent().get(0);
                result.add(q);
            }
        }
        return result;
    }

    public List<Question> findQuestionsByMatrix(List<QuestionCountDto> matrix) {
        List<Question> result = new ArrayList<>();
        for (QuestionCountDto item: matrix) {
            QuestionLevel level = item.getLevel();
            Topic topic = topicRepository.findById(item.getTopic().getId()).orElseThrow(() -> new RuntimeException("Chủ đề kiến thức không tồn tại"));
            int numberOfQuestions = item.getNumberOfQuestions();
            List<Question> questions = getRandomQuestions(level, topic, numberOfQuestions, QuestionStatus.APPROVED);
            result.addAll(questions);
        }
        return result;
    }

    private void changeStatus(List<Integer> qIds, QuestionStatus status) {
        List<Question> founds = questionRepository.findByIdIn(qIds);
        for (Question q : founds) {
            q.setStatus(status);
        }
        questionRepository.saveAll(founds);
    }

    public void approve(List<Integer> qIds) {
        changeStatus(qIds, QuestionStatus.APPROVED);
    }

    public void archive(List<Integer> qIds) {
        changeStatus(qIds, QuestionStatus.ARCHIVED);
    }
}
