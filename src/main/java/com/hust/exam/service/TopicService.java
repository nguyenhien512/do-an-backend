package com.hust.exam.service;

import com.hust.exam.DTO.TopicDto;
import com.hust.exam.mapper.TopicMapper;
import com.hust.exam.models.Topic;
import com.hust.exam.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicService {

    @Autowired
    TopicRepository topicRepository;

    @Autowired
    TopicMapper topicMapper;

    public List<TopicDto> getAll() {
        return topicMapper.toTopicDtoList(topicRepository.findAll());
    }

    public TopicDto createTopic(TopicDto dto) {
        Topic topic = topicMapper.toTopicEntity(dto);
        return topicMapper.toTopicDto(topicRepository.save(topic));
    }

    public TopicDto updateTopic(TopicDto dto) {
        Topic topic = topicMapper.toTopicEntity(dto);
        return topicMapper.toTopicDto(topicRepository.save(topic));
    }

    public void deleteTopic(int topicId) {
        Topic topic = topicRepository.findById(topicId).orElseThrow();
        if (topic.getQuestions().size() > 0) {
            throw new RuntimeException("Không thể xóa chủ đề này vì đã sử dụng trong câu hỏi");
        }
        topicRepository.delete(topic);
    }

}
