package com.example.pdd.repository;

import com.example.pdd.model.PddQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PddQuestionRepository extends JpaRepository<PddQuestion, Integer>{
}
