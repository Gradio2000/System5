package com.example.fnsConverter.controller;

import com.example.fnsConverter.service.FnsService;
import com.example.system5.dto.UserDto;
import com.example.system5.util.AuthUser;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Controller
@RequestMapping("fnsconverter")
public class FnsController {
    @GetMapping("/converter")
    public String getConverterPage(@AuthenticationPrincipal AuthUser authUser, Model model){
        model.addAttribute("user", UserDto.getInstance(authUser.getUser()));
        return "fnsconverter/fnsConverter";
    }

    @PostMapping("/fileUpload")
    public String fileUpload(@RequestParam MultipartFile file, @AuthenticationPrincipal AuthUser authUser,
                             Model model){
        try {
            Map<String, String> resultMap = FnsService.loadUNOfile(file);
            model.addAttribute("resultMap", resultMap);
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/fnsconverter/converter?error=100";
        }
        model.addAttribute("user", UserDto.getInstance(authUser.getUser()));

        return "/fnsconverter/report";
    }


}
