package com.example.docs.controller;

import com.example.docs.dto.BusinessDto;
import com.example.docs.model.Business;
import com.example.docs.repository.BusinessRepository;
import com.example.system5.dto.UserDto;
import com.example.system5.util.AuthUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/docs")
@Slf4j
public class BusinessController {

   private final BusinessRepository businessRepository;

    public BusinessController(BusinessRepository businessRepository) {
        this.businessRepository = businessRepository;
    }

    @GetMapping("/getAllBuiseness")
    public String getAllBusiness(@AuthenticationPrincipal AuthUser authUser, Model model){
        log.info(new Object(){}.getClass().getEnclosingMethod().getName() + " " +
                authUser.getUser().getName());
        model.addAttribute("user", UserDto.getInstance(authUser.getUser()));


        List<Business> businessList = businessRepository.findAll();
        List<BusinessDto> businessDtoList = businessList.stream()
                .map(BusinessDto::getInstance)
                .collect(Collectors.toList());
        model.addAttribute(businessDtoList);
        return "docs/allbusiness";
    }

    @GetMapping("/getBusinessById/{businessDtoId}")
    public String getBusinessById(@AuthenticationPrincipal AuthUser authUser,
                                  @PathVariable Integer businessDtoId, Model model){
        log.info(new Object(){}.getClass().getEnclosingMethod().getName() + " " +
                authUser.getUser().getName());
        model.addAttribute("user", UserDto.getInstance(authUser.getUser()));

        Business business = businessRepository.findById(businessDtoId).orElse(null);
        assert business != null;
        model.addAttribute(business);
        return "docs/business";
    }

    @PostMapping("/addbusiness")
    public String addBusines(@RequestParam String businessName){
        Business business = new Business();
        business.setBusinessName(businessName);
        businessRepository.save(business);
        return "redirect:/docs/getAllBuiseness";
    }

}
