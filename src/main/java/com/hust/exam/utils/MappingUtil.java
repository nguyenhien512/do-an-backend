package com.hust.exam.utils;

import com.hust.exam.enumobject.AnswerKey;
import com.hust.exam.models.MappingRule;

import java.util.Arrays;
import java.util.List;

public class MappingUtil {

    public static AnswerKey mapForward(AnswerKey originalKey, MappingRule mappingRule) {
        String[] rule = mappingRule.getRule().split(",");
        List<String> from = Arrays.asList(rule[0].split(""));
        List<String> to = Arrays.asList(rule[1].split(""));
        int index = from.indexOf(originalKey.name());
        String result = to.get(index);
        return AnswerKey.valueOf(result);
    }

    public static AnswerKey mapBackward(AnswerKey transformedKey, MappingRule mappingRule) {
        String[] rule = mappingRule.getRule().split(",");
        List<String> from = Arrays.asList(rule[0].split(""));
        List<String> to = Arrays.asList(rule[1].split(""));
        int index = to.indexOf(transformedKey.name());
        String result = from.get(index);
        return AnswerKey.valueOf(result);
    }
}
