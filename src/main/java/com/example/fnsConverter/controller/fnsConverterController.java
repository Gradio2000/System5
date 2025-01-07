package com.example.fnsConverter.controller;


import com.example.fnsConverter.service.FnsService;
import com.example.fnsConverter.service.SumInWords;
import com.example.system5.dto.UserDto;
import com.example.system5.util.AuthUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller()
@RequestMapping("/fnsconverter")
public class fnsConverterController {

    @Autowired
    private FnsService fnsService;

    @PostMapping("/getTableView")
    public String getTableWiew(@RequestParam String stringTo,
                               @AuthenticationPrincipal AuthUser authUser,
                               Model model) {

        model.addAttribute("user", UserDto.getInstance(authUser.getUser()));
        model.addAttribute("resultMap", fnsService.createResultMap(stringTo));
        model.addAttribute("stringMap", stringTo);


        return "/fnsconverter/report";
    }


    @PostMapping("getPlatView")
    public String getPlatWiew(@RequestParam String stringTo,
                              @AuthenticationPrincipal AuthUser authUser,
                              Model model) {

            Map<String, String> resultMap = fnsService.createResultMap(stringTo);

        model.addAttribute("user", UserDto.getInstance(authUser.getUser()));
        model.addAttribute("resultMap", resultMap);
        model.addAttribute("stringMap", stringTo);
        model.addAttribute("sumProp",
                SumInWords.moneyInWords(
                        resultMap.get("Сумма платежа - указывается в копейках - реквизит (7)")
                                .replace("-", "")));

        return "/fnsconverter/plat";
    }


}
