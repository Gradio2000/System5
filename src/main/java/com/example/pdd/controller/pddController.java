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
import com.example.system5.dto.UserDto;
import com.example.system5.util.AuthUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/pdd/{biletNumber}")
    public String getQuestionsByBiletNumber(@PathVariable int biletNumber,
                                                       Model model,
                                                       @AuthenticationPrincipal AuthUser authUser){
        log.info(new Object(){}.getClass().getEnclosingMethod().getName() + " " +
                authUser.getUser().getName());

        model.addAttribute("user", UserDto.getInstance(authUser.getUser()));
        model.addAttribute("questionList", pddQuestionRepository.findAllByBiletNumber(biletNumber));
        return "pdd/processPdd";
    }

    @GetMapping("/conv")
    @ResponseBody
    public HttpStatus convert(){

        for (int i = 40; i <= 40 ; i++) {
            Test test = new Test();
            test.setTestName("Билет " + i);
            test.setDeleted(false);
            test.setGroupId(5);
            test.setUsed(false);
            test = testReposytory.save(test);


            List<PddQuestion> pddQuestionList = pddQuestionRepository.findAllByBiletNumber(i);
            List<Question> questionList = new ArrayList<>();
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
                    resultAnswer.setAnswerName(pddAnswer.getAnswerName());
                    resultAnswer.setIsRight(pddAnswer.getIsRight());
                    resultAnswerList.add(resultAnswer);
                }
                answerRepository.saveAll(resultAnswerList);

            }
        }



        return HttpStatus.OK;
    }
}
