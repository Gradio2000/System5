package com.example.priziv.controller;

import com.example.priziv.dto.PrizivDto;
import com.example.priziv.model.Ill;
import com.example.priziv.model.Priziv;
import com.example.priziv.repository.IllRepository;
import com.example.priziv.repository.PrizivRepository;
import com.example.priziv.service.PrizivService;
import com.example.system5.util.AuthUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Slf4j
public class PrizivController {
    private final PrizivRepository prizivRepository;
    private final PrizivService prizivService;
    private final IllRepository illRepository;

    public PrizivController(PrizivRepository prizivRepository, PrizivService prizivService, IllRepository illRepository) {
        this.prizivRepository = prizivRepository;
        this.prizivService = prizivService;
        this.illRepository = illRepository;
    }

    @GetMapping("/priziv")
    public String getPriziv(@AuthenticationPrincipal AuthUser authUser,
                            Model model){

        log.info(new Object(){}.getClass().getEnclosingMethod().getName() + " " +
                authUser.getUser().getName());
        prizivService.getAllPriziv(authUser, model);
        return "/priziv/priziv";
    }

    @GetMapping ("/priziv/ill")
    public String getAllIll(@AuthenticationPrincipal AuthUser authUser,
                            Model model){
        log.info(new Object(){}.getClass().getEnclosingMethod().getName() + " " +
                authUser.getUser().getName());
        prizivService.getAllPriziv(authUser, model);
        return "priziv/ill";
    }

    @PostMapping("/priziv/change")
    @ResponseBody
    public Integer prizivChange(@ModelAttribute PrizivDto prizivDto,
                                @AuthenticationPrincipal AuthUser authUser){
        log.info(new Object(){}.getClass().getEnclosingMethod().getName() + " " +
                authUser.getUser().getName());

        if (prizivDto.getGetPassports() == null){
            prizivDto.setGetPassports(false);
        }
        if (prizivDto.getProcessed() == null){
            prizivDto.setProcessed(false);
        }

        prizivRepository.save(Priziv.getInstance(prizivDto));

        return prizivRepository.findAll().stream().
                mapToInt(Priziv::getIssued)
                .sum();
    }

    @PostMapping("/addPriziv")
    public String addPriziv(@ModelAttribute PrizivDto prizivDto,
                            @AuthenticationPrincipal AuthUser authUser){
        log.info(new Object(){}.getClass().getEnclosingMethod().getName() + " " +
                authUser.getUser().getName());

        prizivDto.setProcessed(false);
        prizivDto.setGetPassports(false);
        prizivDto.setIssued(0);
        if (prizivDto.getPeopleAmmount() == null){
            prizivDto.setPeopleAmmount(0);
        }
        prizivRepository.save(Priziv.getInstance(prizivDto));
        return "redirect:/priziv";
    }

    @PostMapping("/priziv/addIlled")
    @ResponseBody
    public Priziv addIlled(@RequestParam Integer prizivId, @RequestParam String fio,
                           @AuthenticationPrincipal AuthUser authUser){
        log.info(new Object(){}.getClass().getEnclosingMethod().getName() + " " +
                authUser.getUser().getName());

        Priziv priziv = prizivRepository.findById(prizivId).orElse(null);
        assert priziv != null;
        List<Ill> illList = priziv.getIllList();
        illList.add(new Ill(fio, prizivId));
        priziv.setIllList(illList);
        return prizivRepository.save(priziv);
    }

    @GetMapping("priziv/{prizivId}")
    @ResponseBody
    public Priziv getPrizivById(@PathVariable Integer prizivId,
                                @AuthenticationPrincipal AuthUser authUser){
        log.info(new Object(){}.getClass().getEnclosingMethod().getName() + " " +
                authUser.getUser().getName());

        return prizivRepository.findById(prizivId).orElse(null);
    }

    @PostMapping("/priziv/deleteIlled")
    @ResponseBody
    public Priziv deleteIlled(@RequestParam Integer illedId,
                              @RequestParam Integer prizivId,
                              @AuthenticationPrincipal AuthUser authUser){
        log.info(new Object(){}.getClass().getEnclosingMethod().getName() + " " +
                authUser.getUser().getName());

        illRepository.deleteById(illedId);
        return prizivRepository.findById(prizivId).orElse(null);
    }
}

