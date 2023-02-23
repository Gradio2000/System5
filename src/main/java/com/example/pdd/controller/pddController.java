package com.example.pdd.controller;

import com.example.pdd.model.PddQuestion;
import com.example.pdd.repository.PddQuestionRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class pddController {
    private final PddQuestionRepository pddQuestionRepository;

    public pddController(PddQuestionRepository pddQuestionRepository) {
        this.pddQuestionRepository = pddQuestionRepository;
    }

    @GetMapping("/pdd/{biletNumber}")
    @ResponseBody
    public List<PddQuestion> getQuestionsByBiletNumber(@PathVariable int biletNumber){
        List<PddQuestion> pddQuestions = pddQuestionRepository.findAllByBiletNumber(biletNumber);
        return pddQuestions;
    }
}
