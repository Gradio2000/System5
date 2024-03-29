package com.example.system5.controller;


import com.example.system5.dto.UserDto;
import com.example.system5.model.*;
import com.example.system5.repository.CommEmplRepository;
import com.example.system5.repository.System5Repository;
import com.example.system5.repository.UserRepository;
import com.example.system5.service.system5Service.GetTotalMarkService;
import com.example.system5.service.system5Service.SaveOrUpdateSystem5Service;
import com.example.system5.service.system5Service.SortService;
import com.example.system5.util.AuthUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;


@Controller
@Slf4j
public class System5Controller {

    private final System5Repository system5Repository;
    private final UserRepository userRepository;
    private final GetTotalMarkService getTotalMarkService;
    private final SaveOrUpdateSystem5Service saveOrUpdateSystem5Service;
    private final SortService sortService;
    private final CommEmplRepository commEmplRepository;


    public System5Controller(System5Repository system5Repository,
                             UserRepository userRepository, GetTotalMarkService getTotalMarkService,
                             SaveOrUpdateSystem5Service saveOrUpdateSystem5Service,
                             SortService sortService, CommEmplRepository commEmplRepository) {
        this.system5Repository = system5Repository;
        this.userRepository = userRepository;
        this.getTotalMarkService = getTotalMarkService;
        this.saveOrUpdateSystem5Service = saveOrUpdateSystem5Service;
        this.sortService = sortService;
        this.commEmplRepository = commEmplRepository;
    }

    @GetMapping(value = "/list")
    public String getAll(@AuthenticationPrincipal AuthUser authUser, Model model,
                         @RequestParam (required = false) Integer year){
        log.info(new Object(){}.getClass().getEnclosingMethod().getName() + " " +
                authUser.getUser().getName());

        model.addAttribute("user", UserDto.getInstance(authUser.getUser()));

        if (year == null){
            year = LocalDate.now().getYear();
        }
        Integer finalYear = year;

        List<System5> system5List = system5Repository.findAllByUserId(authUser.getUser().getUserId()).stream()
                .filter(system5 -> Objects.equals(system5.getYear(), finalYear))
                .collect(Collectors.toList());
        model.addAttribute("system5List", sortService.sortSystem5(system5List));

        List<Month> monthList = Arrays.stream(Month.values()).collect(Collectors.toList());
        for(System5 system5 : system5List){
            monthList.removeIf(m -> (m.name().equals(system5.getMonth())));
        }
        model.addAttribute(monthList);

        System5 system5 = new System5();
        model.addAttribute(system5);

        System5empl system5empl = new System5empl();
        model.addAttribute(system5empl);

        boolean employer = false;
        model.addAttribute("employer", employer);

        List<User> userList = userRepository.findAllByDeleted(false).stream()
                .filter(u -> !Objects.equals(u.getUserId(), authUser.getUser().getUserId()))
                .filter(user -> !user.getLogin().equals("admin"))
                .collect(Collectors.toList());
        model.addAttribute(userList);


        model.addAttribute("selectedYear", finalYear);
        model.addAttribute("currentYear", LocalDate.now().getYear());
        model.addAttribute("years", system5Repository.getYears());
        return "sys5pages/lists";
    }

    @GetMapping("/list/{id}")
    public String getByUserId(@PathVariable Integer id,
                              @ModelAttribute System5 system5,
                              BindingResult bindingResult,
                              @ModelAttribute System5empl system5empl,
                              BindingResult bindingResult1,
                              Model model,
                              @AuthenticationPrincipal AuthUser authUser,
                              @RequestParam (required = false) Integer year){
        User user = authUser.getUser();
        log.info(user.getName() + " enter into controller /list/" + id);

        if (year == null){
            year = LocalDate.now().getYear();
        }
        Integer finalYear = year;

        List<System5> system5List = system5Repository.findByUserIdOrderBySystem5Id(id).stream()
                .filter(s5 -> s5.getYear().equals(finalYear))
                .collect(Collectors.toList());

        Map<Integer, Month> monthMap = new HashMap<>();
        for (System5 system51 : system5List){
            if (system51.getRated() == 0){
                monthMap.put(system51.getSystem5Id(), Month.valueOf(system51.getMonth()));
            }
        }

        boolean employer = true;

        model.addAttribute("system5List", sortService.sortSystem5(system5List));
        model.addAttribute("employer", employer);
        model.addAttribute("months", monthMap);
        model.addAttribute("userId", id);
        model.addAttribute("user", UserDto.getInstance(user));
        model.addAttribute("years", system5Repository.getYears());
        model.addAttribute("selectedYear", finalYear);

        return "sys5pages/lists";
    }


    @PostMapping("/adds")
    public String add(@AuthenticationPrincipal AuthUser authUser,
                      @ModelAttribute @Valid System5 system5,
                      BindingResult bindingResult,
                      @RequestParam int comm_id){

        log.info(new Object(){}.getClass().getEnclosingMethod().getName() + " " +
                authUser.getUser().getName());

        if (bindingResult.hasErrors()){
            return "redirect:/list?error=1";
        }

        Integer year = LocalDate.now().getYear();
        system5.setYear(year);

        System5 systemForUpdate = system5Repository.findByMonthAndUserIdAndYear(system5.getMonth(), authUser.getUser().getUserId(),year);
        if (systemForUpdate != null){
            saveOrUpdateSystem5Service.updateSystem5(systemForUpdate, system5);
        }
        else {
            system5.setUserId(authUser.getUser().getUserId());
            saveOrUpdateSystem5Service.saveSystem5(system5);
        }

        User commUser = userRepository.findById(comm_id).orElse(null);

        if (!commEmplRepository.existsByEmplUserAndCommUser(authUser.getUser(), commUser)){
            commEmplRepository.save(new CommEmpl(authUser.getUser(), commUser));
        }
        return "redirect:/list";
    }


    @PostMapping("/addsempl")
    public String addempl(@AuthenticationPrincipal AuthUser authUser,
                          @ModelAttribute @Valid System5empl system5empl,
                          BindingResult bindingResult){
        log.info(new Object(){}.getClass().getEnclosingMethod().getName() + " " +
                authUser.getUser().getName());

        int userId= system5empl.getUser_id();
        if (bindingResult.hasErrors()){
            return "redirect:/list/" + userId + "?error=2";
        }

        system5empl.setResempl1(system5empl.getResempl1().toUpperCase());
        system5empl.setResempl2(system5empl.getResempl2().toUpperCase());
        system5empl.setResempl3(system5empl.getResempl3().toUpperCase());
        system5empl.setResempl4(system5empl.getResempl4().toUpperCase());
        system5empl.setResempl5(system5empl.getResempl5().toUpperCase());

        System5 system5 = getTotalMarkService.setTotalMarkAndSystem5Save(system5empl, system5Repository, getTotalMarkService);

        List<System5> system5List = system5Repository.findAllByUserId(userId);
        for (System5 system51 : system5List){
            if (system51.getSystem5empl() == null){
                return "redirect:/list/" + userId;
            }
        }

        User emplUser = system5.getUser();
        if (system5.getSystem5empl() != null){
            commEmplRepository.deleteByEmplUserAndCommUser(emplUser, authUser.getUser());
        }
        return "redirect:/list/" + userId;


    }


    @GetMapping("/getMonths")
    @ResponseBody
    public List<String> getMonthsForEditSelfRate(@AuthenticationPrincipal AuthUser authUser,
                                                 @RequestParam Integer year){
        log.info(new Object(){}.getClass().getEnclosingMethod().getName() + " " +
                authUser.getUser().getName());

        if (year == 0){
            year = LocalDate.now().getYear();
        }

        return system5Repository.findAllByUserIdAndYear(authUser.getUser().getUserId(), year).stream()
                .filter(system5 -> system5.getRated() == 0)
                .map(System5::getMonth)
                .collect(Collectors.toList());
    }


}
