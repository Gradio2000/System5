package com.example.qtest.repository;

import com.example.qtest.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {

    @Query(nativeQuery = true,
            value = "SELECT test_id, question_name, question_id, deleted, image_ref, promt " +
                    "FROM q_questions " +
                    "WHERE test_id = :id AND deleted = false ORDER BY test_id")
    List<Question> findAllByTestIdOrderById(Integer id);

    @Query(nativeQuery = true,
            value = "SELECT test_id, question_name, q_questions.question_id, deleted, image_ref, promt, many_choose " +
                    "FROM q_questions JOIN q_questions_for_attempt " +
                    "ON q_questions.question_id = q_questions_for_attempt.question_id " +
                    "WHERE attempt_id = :attemptId ORDER BY q_questions.question_id")
    List<Question> findQuestionsByAttemptId(Integer attemptId);

    @Query(nativeQuery = true, value = "SELECT question_id FROM q_questions_for_attempt")
    Set<Integer> getIds();

    @Transactional
    @Modifying
    @Query(nativeQuery = true,
    value = "UPDATE q_questions SET deleted = true WHERE question_id IN :ids")
    void makeQuestionDeletedTrue(Integer[] ids);

}
