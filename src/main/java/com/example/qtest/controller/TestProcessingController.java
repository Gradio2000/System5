package com.example.qtest.controller;

import com.example.qtest.model.Attempttest;
import com.example.qtest.model.QuestionsForAttempt;
import com.example.qtest.model.ResultTest;
import com.example.qtest.model.Test;
import com.example.qtest.repository.*;
import com.example.qtest.service.ResultTestService;
import com.example.qtest.service.TestService;
import com.example.system5.dto.UserDto;
import com.example.system5.util.AuthUser;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/processing")
public class TestProcessingController {
    private final AttemptestReporitory attemptestReporitory;
    private final TestReposytory testReposytory;
    private final TestService testService;
    private final QuestionForAttemptRepository questionForAttemptRepository;
    private final ResultTestRepository resultTestRepository;
    private final ResultTestService resultTestService;
    private final AppointTestRepository appointTestRepository;

    public TestProcessingController(AttemptestReporitory attemptestReporitory, TestReposytory testReposytory,
                                    TestService testService, QuestionForAttemptRepository questionForAttemptRepository,
                                    ResultTestRepository resultTestRepository, ResultTestService resultTestService,
                                    AppointTestRepository appointTestRepository) {
        this.attemptestReporitory = attemptestReporitory;
        this.testReposytory = testReposytory;
        this.testService = testService;
        this.questionForAttemptRepository = questionForAttemptRepository;
        this.resultTestRepository = resultTestRepository;
        this.resultTestService = resultTestService;
        this.appointTestRepository = appointTestRepository;
    }

    @PostMapping("/start")
    public String startTestProcessing(@AuthenticationPrincipal AuthUser authUser,
                                      Model model, @RequestParam Integer testId,
                                      @RequestParam (required = false) Integer appointTestId){

            Attempttest attempttest = new Attempttest();
            attempttest.setDateTime(new Date());
            attempttest.setUserId(authUser.getUser().getUserId());
            attempttest.setTestId(testId);
            attempttest.setTestResult("Не завершен");
            attemptestReporitory.save(attempttest);

            Test test = testReposytory.findById(testId).orElse(null);
            assert test != null;
            testService.getShuffleTest(test);

            List<QuestionsForAttempt> questionsForAttemptList =
                    testService.convertTestForSaveBeforeTesting(test, attempttest.getId());
            questionForAttemptRepository.saveAll(questionsForAttemptList);

            model.addAttribute("attemptId", attempttest.getId());
            model.addAttribute("user", UserDto.getInstance(authUser.getUser()));
            model.addAttribute("questionList", questionsForAttemptList);
            model.addAttribute("appointTestId", appointTestId);
            return "qtest/process";
    }

    @PostMapping("/saveUserAnswer")
    @ResponseBody
    public HttpStatus saveUserAnswer(@RequestParam Integer attemptId, @RequestParam Integer questionId,
                                     HttpServletRequest request){
        String[] answersIds = request.getParameterMap().get("check");
        List<ResultTest> resultTestList = new ArrayList<>();
        assert answersIds != null : "saveUserAnswer() Пустой массив ответов на вопрос";
        for (String quesId : answersIds) {
            ResultTest resulttest = new ResultTest();
            resulttest.setAttemptId(attemptId);
            resulttest.setQuestionId(questionId);
            resulttest.setAnswerId(Integer.valueOf(quesId));
            resultTestList.add(resulttest);
        }
        resultTestRepository.saveAll(resultTestList);
        return HttpStatus.OK;
    }

    @PostMapping("/finishTest")
    public String finishTest(@RequestParam Integer attemptId, @RequestParam (required = false) Integer appointTestId){
        resultTestService.mainCheck(attemptId);
        if (appointTestId != null){
            appointTestRepository.setAppointTrue(appointTestId);
        }
        return "redirect:/tests/mytests";
    }

    @GetMapping("/continue/{attemptId}")
    public String continueTest(@AuthenticationPrincipal AuthUser authUser,
                               @PathVariable Integer attemptId, Model model){


        List<QuestionsForAttempt> questionsForAttemptList = questionForAttemptRepository.findAllByAttemptId(attemptId);
        Set<Integer> resultTestQuestionsIdsList = resultTestRepository.findAllByAttemptId(attemptId).stream()
                .map(ResultTest::getQuestionId)
                .collect(Collectors.toSet());
        Set<Integer> resultTestAnswerIdsList = resultTestRepository.findAllByAttemptId(attemptId).stream()
                .map(ResultTest::getAnswerId)
                .collect(Collectors.toSet());

        model.addAttribute("attemptId", attemptId);
        model.addAttribute("user", UserDto.getInstance(authUser.getUser()));
        model.addAttribute("questionList", questionsForAttemptList);
        model.addAttribute("resultTestQuestionsIdsList", resultTestQuestionsIdsList);
        model.addAttribute("resultTestAnswerIdsList", resultTestAnswerIdsList);
        return "qtest/process";
    }
}
