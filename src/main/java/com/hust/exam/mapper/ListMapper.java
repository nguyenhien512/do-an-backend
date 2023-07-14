package com.hust.exam.mapper;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ListMapper {

    static <S, T> List<T> mapList(List<S> source, Class<T> targetClass, Function<S,T> mapper) {
        return source
                .stream()
                .map(mapper)
                .collect(Collectors.toList());
    }


}
