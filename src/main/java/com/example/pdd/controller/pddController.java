package com.example.pdd.controller;

import com.example.pdd.repository.PddQuestionRepository;
import com.example.system5.dto.UserDto;
import com.example.system5.util.AuthUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@Slf4j
public class pddController {
    private final PddQuestionRepository pddQuestionRepository;

    public pddController(PddQuestionRepository pddQuestionRepository) {
        this.pddQuestionRepository = pddQuestionRepository;
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
}
