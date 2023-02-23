package com.example.pdd.repository;

import com.example.pdd.model.PddAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PddAnswerRepository extends JpaRepository<PddAnswer, Integer> {
}
