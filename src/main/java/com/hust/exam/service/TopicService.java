package com.hust.exam.service;

import com.hust.exam.DTO.TopicDto;
import com.hust.exam.mapper.TopicMapper;
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
}
