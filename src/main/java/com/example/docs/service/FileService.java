package com.example.docs.service;

import org.springframework.stereotype.Service;
import org.zwobble.mammoth.DocumentConverter;
import org.zwobble.mammoth.Result;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Service
public class FileService {


    public String addDoc(File file, String fileName){
        DocumentConverter converter = new DocumentConverter();
        Result<String> result;
        try {
            result = converter.convertToHtml(file);
            String html = result.getValue(); // The generated HTML

            try (FileWriter fileWriter = new FileWriter(changeExtension(fileName), StandardCharsets.UTF_16))
            {
                fileWriter.write(html);
                return html;
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String changeExtension(String fileName) {
        int i = fileName.lastIndexOf('.');
        String name = fileName.substring(0, i);
        return name + ".html";
    }
}
