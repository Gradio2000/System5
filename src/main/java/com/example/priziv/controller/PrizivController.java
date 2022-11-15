package com.example.priziv.controller;

import com.example.priziv.model.Priziv;
import com.example.priziv.repository.PrizivRepository;
import com.example.system5.dto.UserDto;
import com.example.system5.util.AuthUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@Slf4j
public class PrizivController {
    private final PrizivRepository prizivRepository;

    public PrizivController(PrizivRepository prizivRepository) {
        this.prizivRepository = prizivRepository;
    }

    @GetMapping("/priziv")
    public String getPriziv(@AuthenticationPrincipal AuthUser authUser,
                            Model model){

        log.info(new Object(){}.getClass().getEnclosingMethod().getName() + " " +
                authUser.getUser().getName());

        List<Priziv> prizivList = prizivRepository.findAll();
        model.addAttribute("user", UserDto.getInstance(authUser.getUser()));
        model.addAttribute("prizivList", prizivList);
        return "/priziv/priziv";
    }
}

