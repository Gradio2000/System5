package com.example.pdd.controller;

import com.example.pdd.model.PddAnswer;
import com.example.pdd.model.PddQuestion;
import com.example.pdd.repository.PddQuestionRepository;
import com.example.qtest.model.Answer;
import com.example.qtest.model.Question;
import com.example.qtest.model.Test;
import com.example.qtest.repository.AnswerRepository;
import com.example.qtest.repository.QuestionRepository;
import com.example.qtest.repository.TestReposytory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
public class pddController {
    private final PddQuestionRepository pddQuestionRepository;
    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;
    private final TestReposytory testReposytory;

    public pddController(PddQuestionRepository pddQuestionRepository, QuestionRepository questionRepository,
                         AnswerRepository answerRepository, TestReposytory testReposytory) {
        this.pddQuestionRepository = pddQuestionRepository;
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
        this.testReposytory = testReposytory;
    }


    //скрипт создания тестов по билетам ПДД
    @GetMapping("/conv")
    @ResponseBody
    public HttpStatus convert(){

        for (int i = 1; i <= 40 ; i++) {
            Test test = new Test();
            test.setTestName("Билет " + i);
            test.setDeleted(false);
            test.setGroupId(5);
            test.setUsed(false);
            test = testReposytory.save(test);


            List<PddQuestion> pddQuestionList = pddQuestionRepository.findAllByBiletNumber(i);

            for (PddQuestion pddQuestion: pddQuestionList){
                Question question = new Question();
                question.setQuestionName(pddQuestion.getQuestionName());
                question.setTestId(test.getTestId());
                question.setDeleted(false);
                question.setImageRef(pddQuestion.getImageRef());
                question.setPromt(pddQuestion.getPromt());

                List<PddAnswer>pddAnswers = pddQuestion.getAnswers();
                List<Answer> resultAnswerList = new ArrayList<>();

                question = questionRepository.save(question);

                for (PddAnswer pddAnswer: pddAnswers){
                    Answer resultAnswer = new Answer();
                    resultAnswer.setQuestion(question);
                    resultAnswer.setAnswerName(pddAnswer.getAnswerName().substring(3));
                    resultAnswer.setIsRight(pddAnswer.getIsRight());
                    resultAnswerList.add(resultAnswer);
                }
                answerRepository.saveAll(resultAnswerList);

            }
        }

        return HttpStatus.OK;
    }



    //скрипт изменения в БД вопросов со множественными ответами
    @GetMapping("/conv1")
    @ResponseBody
    public HttpStatus convert1(){
        List<Question> questionList = questionRepository.findAll();
        for (Question question: questionList){
            List<Answer> answers = question.getAnswers();
            int i = 0;
            for (Answer answer: answers){
                if (answer.getIsRight()){
                    i++;
                }
            }
            if (i > 1){
                question.setManyChoose(true);
            }
        }

        questionRepository.saveAll(questionList);
        return HttpStatus.OK;
    }
}
