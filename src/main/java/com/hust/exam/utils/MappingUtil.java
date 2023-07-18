package com.hust.exam.utils;

import com.hust.exam.enumobject.AnswerKey;
import com.hust.exam.models.MappingRule;

import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

public class MappingUtil {

    public static String mapForward(String originalKeys, MappingRule mappingRule) { //key = A
        if (mappingRule == null || originalKeys == null) {
            return originalKeys; //ABCD,BCDA

        }
        String[] rule = mappingRule.getRule().split(","); //ABCD và BCDA
        List<String> from = Arrays.asList(rule[0].split("")); //[A,B,C,D]
        List<String> to = Arrays.asList(rule[1].split("")); //[B, C, D, A]
        List<String> keys = Arrays.asList(originalKeys.split(","));
        StringJoiner joiner = new StringJoiner(",");
        for (String key : keys) {
            int index = from.indexOf(key);
            joiner.add(to.get(index));
        }
        return joiner.toString();
    }

    public static AnswerKey mapForward(AnswerKey originalKey, MappingRule mappingRule) {
        String result = mapForward(originalKey.name(), mappingRule);
        return AnswerKey.valueOf(result);
    }

    public static String mapBackward(String transformedKeys, MappingRule mappingRule) {//key = B
        if (mappingRule == null || transformedKeys == null) {
            return transformedKeys;
        }
        String[] rule = mappingRule.getRule().split(","); //[A,B,C,D]
        List<String> from = Arrays.asList(rule[0].split("")); //[B, C, D, A]
        List<String> to = Arrays.asList(rule[1].split(""));
        List<String> keys = Arrays.asList(transformedKeys.split(","));
        StringJoiner joiner = new StringJoiner(",");
        for (String key : keys) {
            int index = from.indexOf(key);
            joiner.add(to.get(index));
        }
        return joiner.toString();
    }
}
