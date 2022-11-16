package com.example.priziv.controller;

import com.example.priziv.Dto.PrizivDto;
import com.example.priziv.model.Priziv;
import com.example.priziv.repository.PrizivRepository;
import com.example.system5.dto.UserDto;
import com.example.system5.util.AuthUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.stream.Collectors;

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

        List<PrizivDto> prizivList = prizivRepository.findAll(Sort.by("dateArrival", "dateDeparture"))
                .stream()
                .map(PrizivDto::getInstance)
                .collect(Collectors.toList());

        model.addAttribute("user", UserDto.getInstance(authUser.getUser()));
        model.addAttribute("prizivList", prizivList);
        return "/priziv/priziv";
    }

    @PostMapping("/priziv/change")
    @ResponseBody
    public HttpStatus prizivChange(@ModelAttribute PrizivDto prizivDto){
        if (prizivDto.getGetPassports() == null){
            prizivDto.setGetPassports(false);
        }
        if (prizivDto.getProcessed() == null){
            prizivDto.setProcessed(false);
        }

        Priziv priziv = Priziv.getInstance(prizivDto);
        prizivRepository.save(priziv);
        return HttpStatus.OK;
    }

    @PostMapping("/addPriziv")
    public String addPriziv(@ModelAttribute PrizivDto prizivDto){
        prizivDto.setProcessed(false);
        prizivDto.setGetPassports(false);
        prizivDto.setIssued(0);
        prizivDto.setPreparedAndNotIssued(0);
        Priziv priziv = Priziv.getInstance(prizivDto);
        prizivRepository.save(priziv);
        return "redirect:/priziv";
    }
}

