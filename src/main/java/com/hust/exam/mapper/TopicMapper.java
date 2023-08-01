package com.hust.exam.mapper;

import com.hust.exam.DTO.TopicDto;
import com.hust.exam.models.Topic;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TopicMapper {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    ListMapper listMapper;

    public TopicDto toTopicDto(Topic topic) {
        return modelMapper.map(topic, TopicDto.class);
    }

    public List<TopicDto> toTopicDtoList(List<Topic> topics) {
        return listMapper.mapList(topics, TopicDto.class, this::toTopicDto);
    }

    public Topic toTopicEntity(TopicDto dto) {
        TypeMap<TopicDto, Topic> propertyMapper = modelMapper.getTypeMap(TopicDto.class, Topic.class);
        if (propertyMapper == null) {
            propertyMapper = modelMapper.createTypeMap(TopicDto.class, Topic.class);
        }
        propertyMapper.addMappings(mapper -> mapper.skip(Topic::setId));
        return modelMapper.map(dto, Topic.class);
    }

}
