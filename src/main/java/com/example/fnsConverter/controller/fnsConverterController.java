package com.example.fnsConverter.controller;


import com.example.system5.dto.UserDto;
import com.example.system5.util.AuthUser;
import org.json.JSONObject;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller()
@RequestMapping("/fnsconverter")
public class fnsConverterController {

    @PostMapping("/getTableView")
    public String getTableWiew(@RequestParam JSONObject jsonObject,
                               @AuthenticationPrincipal AuthUser authUser,
                               Model model) {

        model.addAttribute("user", UserDto.getInstance(authUser.getUser()));
        model.addAttribute("resultMap", jsonObject.toMap());
        model.addAttribute("jsonObject", jsonObject);

        return "/fnsconverter/report";
    }

    @PostMapping("getPlatView")
    public String getPlatWiew(@RequestParam JSONObject jsonObject,
                               @AuthenticationPrincipal AuthUser authUser,
                               Model model) {

        model.addAttribute("user", UserDto.getInstance(authUser.getUser()));
        model.addAttribute("resultMap", jsonObject.toMap());
        model.addAttribute("jsonObject", jsonObject);

        return "/fnsconverter/plat";
    }
}
