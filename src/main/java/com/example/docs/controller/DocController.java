package com.example.docs.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zwobble.mammoth.DocumentConverter;
import org.zwobble.mammoth.Result;

import java.io.*;
import java.nio.charset.StandardCharsets;

@Controller
@RequestMapping("/docs")
@Slf4j
public class DocController {

    @GetMapping("/getDoc/{fileName}")
    public String getDoc(Model model, @Value("${pathToRepo}") String pathToRepo,
                         @PathVariable String fileName){

        String url = pathToRepo + fileName;

        try (FileReader fileReader = new FileReader(url)) {
            int c;
            StringBuilder text = new StringBuilder();
            while((c = fileReader.read())!=-1){
                text.append((char) c);
            }
            model.addAttribute("htmlFile", text);
        } catch (FileNotFoundException e){
            model.addAttribute("htmlFile", "Файл не найден");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return "docs/viewForDoc";
    }

    @GetMapping("/uploadDoc")
    public String addDoc(Model model){
        DocumentConverter converter = new DocumentConverter();
        Result<String> result;
        try {
            result = converter.convertToHtml(new File("/Users/aleksejlaskin/Documents/repo/123.docx"));
            String html = result.getValue(); // The generated HTML
            model.addAttribute("htmlFile", html);

         try (FileWriter fileWriter = new FileWriter("/Users/aleksejlaskin/Documents/repo/555.html", StandardCharsets.UTF_16))
            {
                fileWriter.write(html);
            }

        } catch (FileNotFoundException e){
            model.addAttribute("htmlFile", "Файл не найден");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "docs/viewForDoc";
    }
}
