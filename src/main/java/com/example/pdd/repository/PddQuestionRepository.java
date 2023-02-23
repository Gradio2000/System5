package com.example.pdd.repository;

import com.example.pdd.model.PddQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PddQuestionRepository extends JpaRepository<PddQuestion, Integer>{
    List<PddQuestion> findAllByBiletNumber(Integer biletNumber);
}
