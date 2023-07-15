package com.hust.exam.repository;

import com.hust.exam.models.MappingRule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MappingRuleRepository extends JpaRepository<MappingRule,Integer> {
}
