package com.example.kanban.controller;

import com.example.kanban.dto.KanbanDto;
import com.example.kanban.model.Kanban;
import com.example.kanban.repository.KanbanRepository;
import com.example.qtest.service.DtoUtils;
import com.example.system5.dto.NewUserDto;
import com.example.system5.dto.UserDto;
import com.example.system5.model.User;
import com.example.system5.repository.UserRepository;
import com.example.system5.util.AuthUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/kanban")
@Slf4j
public class KanbanController {
    private final KanbanRepository kanbanRepository;
    private final UserRepository userRepository;

    public KanbanController(KanbanRepository kanbanRepository, UserRepository userRepository) {
        this.kanbanRepository = kanbanRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/kanban")
    public String getAllKanban(@AuthenticationPrincipal AuthUser authUser,
                               Model model){
        log.info(new Object(){}.getClass().getEnclosingMethod().getName() + " " +
                authUser.getUser().getName());

        model.addAttribute("user", UserDto.getInstance(authUser.getUser()));
        List<Kanban> kanbans = kanbanRepository.findAll(Sort.by("taskEndDate"));

        List<KanbanDto> kanbanList = kanbans.stream()
                .filter(kanban -> !kanban.getArch())
                .map(KanbanDto::getInstance)
                .collect(Collectors.toList());

        List<KanbanDto> kanbanArch = kanbans.stream()
                .filter(Kanban::getArch)
                .map(KanbanDto::getInstance)
                .collect(Collectors.toList());

        model.addAttribute("kanbanArch", kanbanArch);
        model.addAttribute("kanbanList", kanbanList);
        model.addAttribute("kanban", new Kanban());
        return "kanban/kanban";
    }

    @PostMapping("/move")
    @ResponseBody
    public HttpStatus moveKanbanTask(@RequestParam String columnId,
                                     @RequestParam Integer kanbanId,
                                     @AuthenticationPrincipal AuthUser authUser){
        log.info(new Object(){}.getClass().getEnclosingMethod().getName() + " " +
                authUser.getUser().getName());

        Kanban kanban = kanbanRepository.findById(kanbanId).orElse(null);
        assert kanban != null;
        switch (columnId){
            case ("col1"):
                kanban.setStarted(true);
                kanban.setContinues(false);
                kanban.setFinished(false);
                kanbanRepository.save(kanban);
                break;
            case ("col2"):
                kanban.setStarted(false);
                kanban.setContinues(true);
                kanban.setFinished(false);
                kanbanRepository.save(kanban);
                break;
            case ("col3"):
                kanban.setStarted(false);
                kanban.setContinues(false);
                kanban.setFinished(true);
                kanbanRepository.save(kanban);
                break;
        }
        return HttpStatus.OK;
    }

    @PostMapping("/addNewKanban")
    public String addNewKanban(@ModelAttribute Kanban kanban,
                               @AuthenticationPrincipal AuthUser authUser,
                               @RequestParam String date){
        log.info(new Object(){}.getClass().getEnclosingMethod().getName() + " " +
                authUser.getUser().getName());

        kanban.setUser(authUser.getUser());
        Date date1 = java.sql.Date.valueOf(LocalDate.parse(date));
        kanban.setTaskEndDate(date1);
        kanban.setArch(false);
        kanbanRepository.save(kanban);
        return "redirect:kanban";
    }

    @PostMapping("/delete")
    @ResponseBody
    public HttpStatus deleteKanban(@RequestParam Integer kanbanId,
                                   @AuthenticationPrincipal AuthUser authUser){
        log.info(new Object(){}.getClass().getEnclosingMethod().getName() + " " +
                authUser.getUser().getName());
        Kanban kanban = kanbanRepository.findById(kanbanId).orElse(null);
        assert kanban != null;
        if (kanban.getFinished()){
            kanban.setArch(true);
            kanbanRepository.save(kanban);
        }
        else {
            kanbanRepository.deleteById(kanbanId);
        }
        return HttpStatus.OK;
    }

    @GetMapping("getKanban")
    @ResponseBody
    public KanbanDto getKanbanById(@RequestParam Integer kanId,
                                   @AuthenticationPrincipal AuthUser authUser){
        log.info(new Object(){}.getClass().getEnclosingMethod().getName() + " " +
                authUser.getUser().getName());

        return KanbanDto.getInstance(Objects.requireNonNull(kanbanRepository.findById(kanId).orElse(null)));
    }

    @PostMapping("/editName")
    @ResponseBody
    public HttpStatus editName(@RequestParam String kanbanName, @RequestParam Integer kanbanId,
                               @AuthenticationPrincipal AuthUser authUser){
        log.info(new Object(){}.getClass().getEnclosingMethod().getName() + " " +
                authUser.getUser().getName());

        Kanban kanban = kanbanRepository.findById(kanbanId).orElse(null);
        assert kanban != null;
        kanban.setKanbanName(kanbanName);
        kanbanRepository.save(kanban);
        return HttpStatus.OK;
    }

    @PostMapping("/editDescribe")
    @ResponseBody
    public HttpStatus editDescribe(@RequestParam String describe, @RequestParam Integer kanbanId,
                                   @AuthenticationPrincipal AuthUser authUser){
        log.info(new Object(){}.getClass().getEnclosingMethod().getName() + " " +
                authUser.getUser().getName());

        Kanban kanban = kanbanRepository.findById(kanbanId).orElse(null);
        assert kanban != null;
        kanban.setDescribe(describe);
        kanbanRepository.save(kanban);
        return HttpStatus.OK;
    }


    @PostMapping("/editDate")
    @ResponseBody
    public HttpStatus editDate(@RequestParam String endDate, @RequestParam Integer kanbanId,
                               @AuthenticationPrincipal AuthUser authUser){
        log.info(new Object(){}.getClass().getEnclosingMethod().getName() + " " +
                authUser.getUser().getName());

        Kanban kanban = kanbanRepository.findById(kanbanId).orElse(null);
        assert kanban != null;
        Date date = java.sql.Date.valueOf(LocalDate.parse(endDate));
        kanban.setTaskEndDate(date);
        kanbanRepository.save(kanban);
        return HttpStatus.OK;
    }

    @GetMapping("/getUserDtoList")
    @ResponseBody
    public List<NewUserDto> getUserDtoList(@RequestParam Integer kanId, Model model,
                                                @AuthenticationPrincipal AuthUser authUser){

        log.info(new Object(){}.getClass().getEnclosingMethod().getName() + " " +
                authUser.getUser().getName());

        List<Integer> userDtoNameOnlyListIds = Objects.requireNonNull(kanbanRepository
                        .findById(kanId).orElse(null)).getUserList().stream()
                .map(User::getUserId)
                .collect(Collectors.toList());

        List<NewUserDto> userDtoList = DtoUtils.convertToUserDtoNameOnlyList(userRepository.findAll().stream()
                .filter(user -> !user.getDeleted())
                .filter(user -> !user.getName().equals("admin"))
                .collect(Collectors.toList()));

        userDtoList.removeIf(userDtoNameOnly -> userDtoNameOnlyListIds.contains(userDtoNameOnly.getUserId()));

        model.addAttribute("userDtoList", userDtoList);
        return userDtoList;
    }

    @PostMapping("/editMembers")
    @ResponseBody
    public NewUserDto editMembers(@RequestParam Integer memberSelect, @RequestParam Integer kanbanId,
                                       @AuthenticationPrincipal AuthUser authUser){
        log.info(new Object(){}.getClass().getEnclosingMethod().getName() + " " +
                authUser.getUser().getName());

        User user = userRepository.findById(memberSelect).orElse(null);
        Kanban kanban = kanbanRepository.findById(kanbanId).orElse(null);
        assert kanban != null;
        List<User> userList = kanban.getUserList();
        userList.add(user);
        kanbanRepository.save(kanban);
        assert user != null;
        return new NewUserDto.Builder()
                .withId(user.getUserId())
                .withName(user.getName())
                .build();
    }

    @PostMapping("/delMember")
    @ResponseBody
    public NewUserDto delMember(@RequestParam Integer userId, @RequestParam Integer kanId,
                                     @AuthenticationPrincipal AuthUser authUser){
        log.info(new Object(){}.getClass().getEnclosingMethod().getName() + " " +
                authUser.getUser().getName());

        Kanban kanban = kanbanRepository.findById(kanId).orElse(null);
        assert kanban != null;
        List<User> userList = kanban.getUserList();
        userList.removeIf(user -> Objects.equals(user.getUserId(), userId));
        kanban.setUserList(userList);
        kanbanRepository.save(kanban);
        User user = userRepository.findById(userId).orElse(null);
        assert user != null;
        return new NewUserDto.Builder()
                .withId(user.getUserId())
                .withName(user.getName())
                .build();
    }

    @PostMapping("/delArch")
    @ResponseBody
    public HttpStatus delArch(@RequestParam Integer kanId, @AuthenticationPrincipal AuthUser authUser){

        log.info(new Object(){}.getClass().getEnclosingMethod().getName() + " " +
                authUser.getUser().getName());

        Kanban kanban = kanbanRepository.findById(kanId).orElse(null);
        assert kanban != null;
        kanban.setArch(false);
        kanbanRepository.save(kanban);
        return HttpStatus.OK;
    }
}
