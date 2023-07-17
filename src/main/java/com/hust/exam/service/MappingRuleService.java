package com.hust.exam.service;

import com.hust.exam.models.MappingRule;
import com.hust.exam.repository.MappingRuleRepository;
import com.hust.exam.utils.RandomHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MappingRuleService {

    @Autowired
    MappingRuleRepository mappingRuleRepository;

    public MappingRule getRandomRule() {
        int mappingRuleId = RandomHelper.getRandomInt(1, 25);
        return mappingRuleRepository.findById(mappingRuleId).get();
    }
}
