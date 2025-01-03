package com.example._fileUpload.controller;

import com.example.converter.service.ImportExcel;
import com.example.converter.service.LoadFile;
import com.example.fnsConverter.model.uno.RootTagUno;
import com.example.fnsConverter.service.Converters;
import com.example.fnsConverter.service.FnsService;
import com.example.system5.dto.UserDto;
import com.example.system5.util.AuthUser;
import org.json.JSONObject;
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
public class FileConverterController {

    @Autowired
    FnsService fnsService;

    @Autowired
    Converters converters;

    @GetMapping("/converter")
    public String getConverterPage(@AuthenticationPrincipal AuthUser authUser, Model model){
        model.addAttribute("user", UserDto.getInstance(authUser.getUser()));
        model.addAttribute("filePath", ImportExcel.getFilename());
        return "_fileConverter/converter";
    }

    @PostMapping("/fileUpload")
    public String fileUpload(@RequestParam MultipartFile file, @AuthenticationPrincipal AuthUser authUser,
                             Model model){

        if (Objects.requireNonNull(file.getOriginalFilename()).contains("UNO")) {
            try {
                RootTagUno rootTagUno = fnsService.loadUNOfile(file);
                Map<String, String> resultMap = fnsService.createMap(rootTagUno);
                Map<String, String[]> bigMap = fnsService.convertMap(resultMap, rootTagUno);

                JSONObject jsonObject = converters.createJSONObject(resultMap);

                model.addAttribute("resultMap", resultMap);
                model.addAttribute("bigMap", bigMap);
                model.addAttribute("jsonObject", jsonObject);

            } catch (Exception e) {
                e.printStackTrace();
                return "redirect:/fileConverter/converter?error=100";
            }
            model.addAttribute("user", UserDto.getInstance(authUser.getUser()));

//            return "/fnsconverter/report";
            return "/fnsconverter/plat";
        }
        else
            try {
            LoadFile.loadFile(file);
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/fileConverter/converter?error=100";
        }
        model.addAttribute("user", UserDto.getInstance(authUser.getUser()));
        return "/converter/download";
    }
}
