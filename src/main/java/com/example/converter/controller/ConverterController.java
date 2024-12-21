package com.example.converter.controller;

import com.example.converter.service.ImportExcel;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Controller
@RequestMapping("/converter")
public class ConverterController {




    @GetMapping("/download")
    public ResponseEntity<InputStreamResource> downloadFile() throws IOException {

        File file = new File(ImportExcel.getFilename());
        InputStreamResource resource = new InputStreamResource(Files.newInputStream(file.toPath()));

        return ResponseEntity.ok()
                // Content-Disposition
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getName())
                // Content-Type
                .contentType(MediaType.TEXT_PLAIN)
                // Contet-Length
                .contentLength(file.length()) //
                .body(resource);
    }
}
