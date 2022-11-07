package com.example.system5.controller.userController;

import com.example.system5.dto.UserDto;
import com.example.system5.model.FormFinishReg;
import com.example.system5.repository.PositionRepository;
import com.example.system5.util.AuthUser;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AccessController {
    private final PositionRepository positionRepository;

    public AccessController(PositionRepository positionRepository) {
        this.positionRepository = positionRepository;
    }

    @GetMapping("/loginTrue")
    public String loginTrue(@AuthenticationPrincipal AuthUser authUser,
                            Model model, HttpServletRequest request){

        model.addAttribute("user", UserDto.getInstance(authUser.getUser()));

        if (authUser.getUser().getName() == null){
            model.addAttribute("formFinishReg", new FormFinishReg());
            request.setAttribute("error", "Завершите процедуру регистрации");
            model.addAttribute("positionList", positionRepository.findAll());
            return "sys5pages/registrationnext";
        }
        if (authUser.getUser().getDeleted()){
            return "redirect:/logout";
        }
        return "redirect:/kanban/kanban";
    }

    @GetMapping("/accessDenied")
    public String accessDenied(@AuthenticationPrincipal AuthUser authUser,
                               Model model){
        UserDto userDto = UserDto.getInstance(authUser.getUser());
        model.addAttribute("user", userDto);
        return "sys5pages/accessDenied";
    }
}
