package com.example.docs.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/doc")
@Slf4j
public class DocController {

    @PostMapping("/adddoc")
    public String addDoc(){
        return "aaa";
    }
}
