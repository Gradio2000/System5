package com.example.fnsConverter.controller;


import com.example.fnsConverter.service.Converters;
import com.example.system5.dto.UserDto;
import com.example.system5.util.AuthUser;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller()
@RequestMapping("/fnsconverter")
public class fnsConverterController {

    @Autowired
    private Converters converters;

    @PostMapping("/getTableView")
    public String getTableWiew(@RequestParam JSONObject jsonObject,
                               @AuthenticationPrincipal AuthUser authUser,
                               Model model) {

        model.addAttribute("user", UserDto.getInstance(authUser.getUser()));
        model.addAttribute("resultMap", converters.jsonToMap(jsonObject));

        return "/fnsconverter/report";
    }



}
