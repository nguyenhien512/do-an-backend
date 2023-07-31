package com.hust.exam.service.impl;

import com.hust.exam.DTO.StatisticRecord;
import com.hust.exam.mapper.StatisticMapper;
import com.hust.exam.models.statistic.StudentScore;
import com.hust.exam.repository.ExamRepository;
import com.hust.exam.repository.TestQuestionRelationRepository;
import com.hust.exam.repository.TestRepository;
import com.hust.exam.service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.*;

@Service
public class StatisticServiceImpl implements StatisticService {

    @PersistenceContext
    public EntityManager em;

    @Autowired
    ExamRepository examRepository;

    @Autowired
    TestRepository testRepository;

    @Autowired
    TestQuestionRelationRepository testQuestionRelationRepository;

    @Override
    public List<StatisticRecord> getDataByExam(int examId) {
        List<StatisticRecord> records = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            int studentNum = 0;
            if (i != 9) {
                studentNum = examRepository.findStudentNumByPoint(examId, i + 1, i);
            } else {
                studentNum = examRepository.findStudentNumByPoint10(examId);
            }
            records.add(new StatisticRecord(i, studentNum));
        }
        return records;
    }

    @Override
    public List<StatisticRecord> getDataByQuesAnswer(int examId) {
        List<Integer> questionIds = new HashSet<>(testRepository.findQuestionIdByExam(examId)).stream().toList();
        System.out.println(questionIds);
        List<StatisticRecord> records = new LinkedList<>();
        for(int i = 0; i < questionIds.size(); i++) {
            int correctNum = testQuestionRelationRepository.findStudentNumByQuesId(questionIds.get(i), true);
            int wrongNum = testQuestionRelationRepository.findStudentNumByQuesId(questionIds.get(i), false);
            String questionType = testQuestionRelationRepository.findQuestionType(questionIds.get(i));
            records.add(new StatisticRecord(correctNum, wrongNum, questionIds.get(i), questionType));
        }

        Collections.sort(records,new Comparator<>() {
            public int compare(StatisticRecord s1, StatisticRecord s2) {
                return s1.getLevel().compareToIgnoreCase(s2.getLevel());
            }
        });
        return records;
    }

    @Override
    public List<StatisticRecord> getStudentInfor(int examId) {
        List<StudentScore> students
                = Collections.checkedList(
                em.createNamedQuery("StudentScore").setParameter("examId", examId).getResultList(), StudentScore.class);
        return StatisticMapper.toDtoList(students);
    }

    public Float getAverageScore(int examId) {
        return testRepository.findAverageByExam(examId);
    }
}
