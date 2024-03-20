package com.example.docs.controller;

import com.example.docs.service.FileService;
import com.example.system5.util.AuthUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;


@Controller
@RequestMapping("/docs")
@Slf4j
public class FileLoadController {

    @Autowired
    FileService fileService;

    @PostMapping("/fileUpload")
    public String loadFile(@RequestParam MultipartFile file,
                           @AuthenticationPrincipal AuthUser authUser, @Value("${pathToRepo}") String pathToRepo,
                           Model model) throws IOException {
        log.info(new Object(){}.getClass().getEnclosingMethod().getName() + " " +
                authUser.getUser().getName());

        String fileName = pathToRepo + file.getOriginalFilename();
        File newFile = File.createTempFile("temp", ".docx", null);
        file.transferTo(newFile);
        String html = fileService.addDoc(newFile, fileName);
        model.addAttribute("htmlFile", html);
        return "docs/viewForDoc";
    }
}
