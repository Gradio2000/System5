package com.example.system5.controller.adminController;

import com.example.system5.dto.UserDto;
import com.example.system5.model.Month;
import com.example.system5.model.System5;
import com.example.system5.model.System5empl;
import com.example.system5.model.User;
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
@RequestMapping("/admin")
@Slf4j
public class ArchiveController {
    private final UserRepository userRepository;
    private final System5Repository system5Repository;
    private final SaveOrUpdateSystem5Service saveOrUpdateSystem5Service;
    private final GetTotalMarkService getTotalMarkService;
    private final SortService sortService;

    public ArchiveController(UserRepository userRepository, System5Repository system5Repository,
                             SaveOrUpdateSystem5Service saveOrUpdateSystem5Service,
                             GetTotalMarkService getTotalMarkService, SortService sortService) {
        this.userRepository = userRepository;
        this.system5Repository = system5Repository;
        this.saveOrUpdateSystem5Service = saveOrUpdateSystem5Service;
        this.getTotalMarkService = getTotalMarkService;
        this.sortService = sortService;
    }

    @GetMapping("/archive")
    public String getEmployersList(@AuthenticationPrincipal AuthUser authUser,
                                   Model model){
        log.info(new Object(){}.getClass().getEnclosingMethod().getName() + " " +
                authUser.getUser().getName());

        model.addAttribute("user", UserDto.getInstance(authUser.getUser()));
        model.addAttribute("userList", userRepository.findAll());
        return "sys5pages/employersListForGetArchive";
    }

    @GetMapping("/getUserSystem5Archive/{id}")
    public String getUserListSystem5(@AuthenticationPrincipal AuthUser authUser, @PathVariable Integer id,
                                     Model model, @ModelAttribute @Valid System5 system5,
                                     BindingResult bindingResult,
                                     @ModelAttribute @Valid System5empl system5empl,
                                     BindingResult bindingResult1,
                                     @RequestParam (required = false) Integer year){
        log.info(new Object(){}.getClass().getEnclosingMethod().getName() + " " +
                authUser.getUser().getName());

        model.addAttribute("user", UserDto.getInstance(authUser.getUser()));

        if (year == null){
            year = LocalDate.now().getYear();
        }
        Integer finalYear = year;

        List<String> monthList = Arrays.stream(Month.values())
                .map(Enum::name)
                .collect(Collectors.toList());
        model.addAttribute("monthList", monthList);

        User user = userRepository.findById(id).orElse(null);
        assert user != null;
        List<System5> system5List = user.getSystem5List().stream()
                .filter(s -> Objects.equals(s.getYear(), finalYear))
                .collect(Collectors.toList());
        model.addAttribute("system5List", sortService.sortSystem5(system5List));


        Map<Integer, Month> monthListFromSystem5List = new HashMap<>();
        for (System5 system51 : system5List){
            monthListFromSystem5List.put(system51.getSystem5Id(), Month.valueOf(system51.getMonth()));
        }
        model.addAttribute("monthListForEditSystem5Empl", monthListFromSystem5List);
        model.addAttribute("years", system5Repository.getYears());
        model.addAttribute("userId", id);
        model.addAttribute("selectedYear", finalYear);
        return "sys5pages/ArchiveUserListSystem5";
    }

    @PostMapping("/addFromAdminModule")
    public String addMarkFromAdminModule(@ModelAttribute @Valid System5 system5,
                                         BindingResult bindingResult, @AuthenticationPrincipal AuthUser authUser){

        log.info(new Object(){}.getClass().getEnclosingMethod().getName() + " " +
                authUser.getUser().getName());

        if (bindingResult.hasErrors()){
            return "redirect:/admin/getUserSystem5Archive/" + system5.getUserId() + "?error=1";
        }

        if (system5.getYear() == null){
            system5.setYear(LocalDate.now().getYear());
        }

        System5 systemForUpdate = system5Repository.findByMonthAndYearAndUserId(system5.getMonth(), system5.getYear(), system5.getUserId());
        if (systemForUpdate != null){
            saveOrUpdateSystem5Service.updateSystem5(systemForUpdate, system5);
        }
        else {
            system5.setUserId(system5.getUserId());
            saveOrUpdateSystem5Service.saveSystem5(system5);
        }

        return "redirect:/admin/getUserSystem5Archive/" + system5.getUserId() + "?year=" + system5.getYear();
    }


    @PostMapping("/editSystem5EmplFromAdminModule")
    public String editSystem5EmplFromAdminModule(@ModelAttribute @Valid System5empl system5empl,
                                                 BindingResult bindingResult,
                                                 @AuthenticationPrincipal AuthUser authUser){
        log.info(new Object(){}.getClass().getEnclosingMethod().getName() + " " +
                authUser.getUser().getName());

        if (bindingResult.hasErrors()){
            return "redirect:/admin/getUserSystem5Archive/" + system5empl.getUser_id() + "?error=1";
        }

        System5 system5 = getTotalMarkService.setTotalMarkAndSystem5Save(system5empl, system5Repository, getTotalMarkService);

        return "redirect:/admin/getUserSystem5Archive/" + system5.getUserId() + "?year=" + system5.getYear();
    }
}
