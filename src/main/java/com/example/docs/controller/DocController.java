package com.example.docs.controller;

import com.example.docs.model.Docs;
import com.example.docs.repository.DocsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Controller
@RequestMapping("/docs")
@Slf4j
public class DocController {
    @Autowired
    DocsRepository docsRepository;

    @GetMapping("/getDoc/{docId}")
    public String getDoc(Model model, @Value("${pathToRepo}") String pathToRepo,
                         @PathVariable Integer docId){

        Docs docs = docsRepository.findById(docId).orElse(null);

        assert docs != null;
        String url = pathToRepo + docs.getFileName();

        try (FileReader fileReader = new FileReader(url, StandardCharsets.UTF_16)) {
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



}
