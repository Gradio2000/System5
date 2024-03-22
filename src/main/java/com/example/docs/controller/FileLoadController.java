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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;


@Controller
@RequestMapping("/docs")
@Slf4j
public class FileLoadController {

    @Autowired
    FileService fileService;

    @PostMapping(value = "/fileUpload")
    @ResponseBody
    public String loadFile(@RequestParam MultipartFile file, @RequestParam Integer businessId,
                           @AuthenticationPrincipal AuthUser authUser, @Value("${pathToRepo}") String pathToRepo,
                           Model model) throws IOException {
        log.info(new Object(){}.getClass().getEnclosingMethod().getName() + " " +
                authUser.getUser().getName());

        String simpleFileName = file.getOriginalFilename();
        String fullFileName = pathToRepo + simpleFileName;
        File newFile = File.createTempFile("temp", ".docx", null);
        file.transferTo(newFile);
        return fileService.addDoc(newFile, fullFileName, simpleFileName, businessId);
    }
}
