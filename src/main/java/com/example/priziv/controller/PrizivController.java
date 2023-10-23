package com.example.priziv.controller;

import com.example.priziv.dto.PrizivDto;
import com.example.priziv.model.Ill;
import com.example.priziv.model.Priziv;
import com.example.priziv.model.PrizivMonthYear;
import com.example.priziv.repository.IllRepository;
import com.example.priziv.repository.PrizivMonthYearRepository;
import com.example.priziv.repository.PrizivRepository;
import com.example.priziv.service.PrizivService;
import com.example.system5.dto.UserDto;
import com.example.system5.util.AuthUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@Slf4j
public class PrizivController {
    private final PrizivRepository prizivRepository;
    private final PrizivService prizivService;
    private final IllRepository illRepository;
    private final PrizivMonthYearRepository prizivMonthYearRepository;

    public PrizivController(PrizivRepository prizivRepository, PrizivService prizivService,
                            IllRepository illRepository, PrizivMonthYearRepository prizivMonthYearRepository) {
        this.prizivRepository = prizivRepository;
        this.prizivService = prizivService;
        this.illRepository = illRepository;
        this.prizivMonthYearRepository = prizivMonthYearRepository;
    }

    @GetMapping("/prizivAll")
    public String getAllPrizivNew(@AuthenticationPrincipal AuthUser authUser, Model model){
        log.info(new Object(){}.getClass().getEnclosingMethod().getName() + " " +
                authUser.getUser().getName());
        PrizivMonthYear prizivMonthYear = prizivMonthYearRepository.findFirstByOrderByIdDesc();
        model.addAttribute("user", UserDto.getInstance(authUser.getUser()));
        prizivService.getAllPriziv(model, prizivMonthYear);

        return "/priziv/priziv";
    }

    @GetMapping ("/priziv/ill/{monthYearId}")
    public String getAllIll(@AuthenticationPrincipal AuthUser authUser,
                            Model model,
                            @PathVariable Integer monthYearId){
        log.info(new Object(){}.getClass().getEnclosingMethod().getName() + " " +
                authUser.getUser().getName());

        List<Priziv> prizivList = prizivRepository.findAllByMonthYearId(monthYearId);
        model.addAttribute("user", UserDto.getInstance(authUser.getUser()));
        model.addAttribute("prizivList", prizivList);

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
        if (prizivDto.getIssued() == null){
            prizivDto.setIssued(0);
        }

        prizivRepository.save(Priziv.getInstance(prizivDto));
        return prizivRepository.findAllByMonthYearId(prizivDto.getMonthYearId()).stream()
                .mapToInt(Priziv::getIssued)
                .sum();
    }

    @PostMapping("/addPriziv")
    public String addPriziv(@ModelAttribute PrizivDto prizivDto,
                            @AuthenticationPrincipal AuthUser authUser,
                            Model model){
        log.info(new Object(){}.getClass().getEnclosingMethod().getName() + " " +
                authUser.getUser().getName());

        prizivDto.setProcessed(false);
        prizivDto.setGetPassports(false);
        prizivDto.setIssued(0);
        if (prizivDto.getPeopleAmmount() == null){
            prizivDto.setPeopleAmmount(0);
        }
        prizivRepository.save(Priziv.getInstance(prizivDto));
        PrizivMonthYear prizivMonthYear = prizivMonthYearRepository.findById(prizivDto.getMonthYearId()).orElse(null);
        assert prizivMonthYear != null;
        model.addAttribute("user", UserDto.getInstance(authUser.getUser()));
        prizivService.getAllPriziv(model, prizivMonthYear);
        return "/priziv/priziv";
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

    @GetMapping("/prizivWithTotalIssued/{prizivId}/{prizivMonthId}")
    @ResponseBody
    public Map<String,Object> getPrizivWithTotalIssued(@PathVariable Integer prizivId, @PathVariable Integer prizivMonthId,
                                                       @AuthenticationPrincipal AuthUser authUser){
        log.info(new Object(){}.getClass().getEnclosingMethod().getName() + " " +
                authUser.getUser().getName());

        return prizivService.getResultMapService(prizivId, prizivMonthId);
    }

    @GetMapping("/priziv/getpriziv/{prizivMonthYearId}")
    public String getPrizivByPrizimMonthYearId(@PathVariable Integer prizivMonthYearId,
                                               @AuthenticationPrincipal AuthUser authUser, Model model){

        log.info(new Object(){}.getClass().getEnclosingMethod().getName() + " " +
                authUser.getUser().getName());

        model.addAttribute("user", UserDto.getInstance(authUser.getUser()));
        PrizivMonthYear prizivMonthYear = prizivMonthYearRepository.findById(prizivMonthYearId).orElse(null);
        assert prizivMonthYear != null;
        prizivService.getAllPriziv(model, prizivMonthYear);
        return "/priziv/priziv";
    }

    @PostMapping("/priziv/addPrizivMonthName")
    public String addPrizivMonthName(@RequestParam String monthYearName,
                                     @ModelAttribute PrizivMonthYear prizivMonthYear){

        prizivMonthYear.setMonthYearName(monthYearName);
        prizivMonthYearRepository.save(prizivMonthYear);
        return "/priziv/priziv";
    }

}

