package com.hust.exam.mapper;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class ListMapper {

    public <S, T> List<T> mapList(List<S> source, Class<T> targetClass, Function<S,T> mapper) {
        return source
                .stream()
                .map(mapper)
                .collect(Collectors.toList());
    }


}
