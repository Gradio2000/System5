package com.example.docs.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

@Controller
@RequestMapping("/doc")
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

    @PostMapping("/adddoc")
    public String addDoc(){
        return "aaa";
    }
}
