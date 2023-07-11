package com.hust.exam.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.List;
import java.util.stream.Collectors;

public class RandomHelper {

    private static Random rnd = new Random();

    public static List<Integer> randomIndexes(int range, int size){
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < range; i++) {
            list.add(i);
        }
        Collections.shuffle(list);
        return list.stream().limit(size).collect(Collectors.toList());
    }
}
