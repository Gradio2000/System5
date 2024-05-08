package com.example.docs.controller;

import com.example.docs.dao.DataBaseUserQueryes;
import com.example.docs.dto.DocsDto;
import com.example.docs.model.Docs;
import com.example.docs.repository.DocsRepository;
import com.example.docs.service.FileService;
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

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/ruk_doc")
@Slf4j
public class DocController {
    @Autowired
    private DocsRepository docsRepository;

    @Autowired
    private DataBaseUserQueryes dataBaseUserQueryes;

    @Autowired
    private FileService fileService;

    @GetMapping("/getDoc/{docId}")
    @ResponseBody
    public String getDoc(@Value("${pathToRepo}") String pathToRepo,
                             @PathVariable Integer docId, @AuthenticationPrincipal AuthUser authUser){

        log.info(new Object(){}.getClass().getEnclosingMethod().getName() + " " +
                authUser.getUser().getName());

        Docs docs = docsRepository.findById(docId).orElse(null);

        assert docs != null;
        String fileName = pathToRepo + docs.getFileName();
        return fileService.getHtmlFile(fileName);
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
            return "redirect:/ruk_doc/getBusinessById/" + businessId + "?error=100";
        }

        return "redirect:/ruk_doc/getBusinessById/" + businessId;
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

    @PostMapping("changeRegNumber")
    @ResponseBody
    public HttpStatus changeRegNumber(@AuthenticationPrincipal AuthUser authUser,
                                      @RequestParam (required = false) String regNumber,
                                      @RequestParam (required = false) Integer docId){

        log.info(new Object(){}.getClass().getEnclosingMethod().getName() + " " +
                authUser.getUser().getName());

        Docs docs = docsRepository.findById(docId).orElse(null);
        assert docs != null;
        docs.setRegNumber(regNumber.toUpperCase().replace("\n", " ").replace("  ", " "));
        docsRepository.save(docs);

        return HttpStatus.OK;
    }


}
