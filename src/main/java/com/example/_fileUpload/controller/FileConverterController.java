package com.example._fileUpload.controller;

import com.example.converter.service.ImportToExcelAndWord;
import com.example.converter.service.LoadFile;
import com.example.fnsConverter.model.uno.RootTagUno;
import com.example.fnsConverter.service.Converters;
import com.example.fnsConverter.service.FnsService;
import com.example.system5.dto.UserDto;
import com.example.system5.util.AuthUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;
import java.util.Objects;

@org.springframework.stereotype.Controller
@RequestMapping("/fileConverter")
@Slf4j
public class FileConverterController {

    @Autowired
    FnsService fnsService;
    @Autowired
    Converters converters;

    @GetMapping("/converter")
    public String getConverterPage(@AuthenticationPrincipal AuthUser authUser, Model model){
        log.info(new Object(){}.getClass().getEnclosingMethod().getName() + " " +
                authUser.getUser().getName());
        model.addAttribute("user", UserDto.getInstance(authUser.getUser()));
        model.addAttribute("filePath", ImportToExcelAndWord.getFilename());
        return "_fileConverter/converter";
    }

    @PostMapping("/fileUpload")
    public String fileUpload(@RequestParam MultipartFile file,
                             @AuthenticationPrincipal AuthUser authUser,
                             Model model){
        log.info(new Object(){}.getClass().getEnclosingMethod().getName() + " " +
                authUser.getUser().getName());
        model.addAttribute("user", UserDto.getInstance(authUser.getUser()));

        Map<String, String> resultMap;

        if (Objects.requireNonNull(file.getOriginalFilename()).contains("UNO")) {
            try {
                RootTagUno rootTagUno = fnsService.loadUNOfile(file);
                resultMap = fnsService.createMap(rootTagUno);
            } catch (Exception e) {
                e.printStackTrace();
                return "redirect:/fileConverter/converter?error=100";
            }
            model.addAttribute("resultMap", resultMap);
            model.addAttribute("stringMap", converters.convertMapToString(resultMap));
            return "/fnsconverter/report";
        }

        if (file.getOriginalFilename().contains("2z")){
            int mapSize = fnsService.convert2zFile(file);
            model.addAttribute("mapSize", mapSize);
            return "/converter/download";
        }

        else
            try {
            LoadFile.loadFile(file);
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/fileConverter/converter?error=100";
        }

        model.addAttribute("mapSize", 1);
        return "/converter/download";
    }
}
