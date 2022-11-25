package com.example.priziv.controller;

import com.example.priziv.dto.PrizivDto;
import com.example.priziv.model.Ill;
import com.example.priziv.model.Priziv;
import com.example.priziv.repository.PrizivRepository;
import com.example.priziv.service.PrizivService;
import com.example.system5.util.AuthUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
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

    public PrizivController(PrizivRepository prizivRepository, PrizivService prizivService) {
        this.prizivRepository = prizivRepository;
        this.prizivService = prizivService;
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
        if (prizivDto.getPeopleAmmount() == null){
            prizivDto.setPeopleAmmount(0);
        }
        Priziv priziv = Priziv.getInstance(prizivDto);
        prizivRepository.save(priziv);
        return "redirect:/priziv";
    }

    @PostMapping("/priziv/addIlled")
    @ResponseBody
    public Priziv addIlled(@RequestParam Integer prizivId, @RequestParam String fio){
        Priziv priziv = prizivRepository.findById(prizivId).orElse(null);
        assert priziv != null;
        List<Ill> illList = priziv.getIllList();
        illList.add(new Ill(fio, prizivId));
        priziv.setIllList(illList);
        return prizivRepository.save(priziv);
    }
}

