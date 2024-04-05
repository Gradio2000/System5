package com.example.docs.controller;

import com.example.docs.dao.DataBaseUserQueryes;
import com.example.docs.dto.DocsDto;
import com.example.docs.model.Docs;
import com.example.docs.repository.DocsRepository;
import com.example.system5.dto.UserDto;
import com.example.system5.util.AuthUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/docs")
@Slf4j
public class DocController {
    @Autowired
    private DocsRepository docsRepository;

    @Autowired
    private DataBaseUserQueryes dataBaseUserQueryes;

    @GetMapping("/getDoc/{docId}")
    @ResponseBody
    public StringBuilder getDoc(Model model, @Value("${pathToRepo}") String pathToRepo,
                             @PathVariable Integer docId, @AuthenticationPrincipal AuthUser authUser){

        log.info(new Object(){}.getClass().getEnclosingMethod().getName() + " " +
                authUser.getUser().getName());


        Docs docs = docsRepository.findById(docId).orElse(null);

        assert docs != null;
        String url = pathToRepo + docs.getFileName();
        StringBuilder text = null;

        try (FileReader fileReader = new FileReader(url, StandardCharsets.UTF_16)) {
            int c;
            text = new StringBuilder();
            while((c = fileReader.read())!=-1){
                text.append((char) c);
            }
            model.addAttribute("htmlFile", text);
        } catch (FileNotFoundException e){
            model.addAttribute("htmlFile", "Файл не найден");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return text;
    }

    @PostMapping("/changeDocs")
    @ResponseBody
    public HttpStatus changeDocs(@AuthenticationPrincipal AuthUser authUser,
                                 @RequestParam (required = false) String docName,
                                 @RequestParam (required = false) Integer docId){

        log.info(new Object(){}.getClass().getEnclosingMethod().getName() + " " +
                authUser.getUser().getName());

        Docs docs = docsRepository.findById(docId).orElse(null);
        assert docs != null;
        docs.setDocName(docName.toUpperCase().replace("\n", " ").replace("  ", " "));
        docsRepository.save(docs);
        return HttpStatus.OK;
    }

    @PostMapping("/deleteDocs")
    public String deleteBusiness(@AuthenticationPrincipal AuthUser authUser,@RequestParam String businessId,
                                 @RequestParam (required = false, name = "check") Integer[] docsIds){
        log.info(new Object(){}.getClass().getEnclosingMethod().getName() + " " +
                authUser.getUser().getName());

        try {
            docsRepository.deleteAllById(Arrays.asList(docsIds));
        } catch (NullPointerException e) {
            return "redirect:/docs/getBusinessById/" + businessId + "?error=100";
        }

        return "redirect:/docs/getBusinessById/" + businessId;
    }

    @GetMapping ("/search-doc")
    public String search(@AuthenticationPrincipal AuthUser authUser, @RequestParam (required = false) String findQuery,
                         Model model){
        log.info(new Object(){}.getClass().getEnclosingMethod().getName() + " " +
                authUser.getUser().getName());

        model.addAttribute("user", UserDto.getInstance(authUser.getUser()));

        List<DocsDto> docsDtoList = dataBaseUserQueryes.getDocsByFullTextSearch(findQuery);
        model.addAttribute("docsDtoList", docsDtoList);
        model.addAttribute("");
        return "/docs/search_result";
    }

    @PostMapping("deleteSearchDocs")
    public String deleteSearchDocs(@AuthenticationPrincipal AuthUser authUser,
                                   @RequestParam (required = false, name = "check") Integer[] docsIds){

        log.info(new Object(){}.getClass().getEnclosingMethod().getName() + " " +
                authUser.getUser().getName());

        try {
            docsRepository.deleteAllById(Arrays.asList(docsIds));
        } catch (NullPointerException e) {
            return "redirect:/docs/search-doc/?error=100";
        }

        return "/docs/search_result";
    }


}
