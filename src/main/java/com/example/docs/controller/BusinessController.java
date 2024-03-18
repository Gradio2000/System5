package com.example.docs.controller;

import com.example.docs.dto.BusinessDto;
import com.example.docs.model.Business;
import com.example.docs.repository.BusinessRepository;
import com.example.docs.service.BusinessDeleteException;
import com.example.system5.dto.UserDto;
import com.example.system5.util.AuthUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
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

    @GetMapping("/getAllBusiness")
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
    public String addBusiness(@AuthenticationPrincipal AuthUser authUser, @RequestParam String businessName){
        log.info(new Object(){}.getClass().getEnclosingMethod().getName() + " " +
                authUser.getUser().getName());

        if (businessName.isEmpty()) return "redirect:/docs/getAllBusiness?error=200";

        Business business = new Business();
        business.setBusinessName(businessName);
        businessRepository.save(business);
        return "redirect:/docs/getAllBusiness";
    }

    @PostMapping("/deleteBusiness")
    public String deleteBusiness(@AuthenticationPrincipal AuthUser authUser,
                                 @RequestParam (required = false, name = "check") Integer[] businessIds){
        log.info(new Object(){}.getClass().getEnclosingMethod().getName() + " " +
                authUser.getUser().getName());

        try {
            List<Business> businessList = businessRepository.findAllById(Arrays.asList(businessIds));
            for (Business business : businessList){
                if (!business.getDocsList().isEmpty()){
                    throw new BusinessDeleteException();
                }
            }
            businessRepository.deleteAllById(Arrays.asList(businessIds));
        } catch (NullPointerException e) {
            return "redirect:/docs/getAllBusiness?error=100";
        } catch (BusinessDeleteException e) {
            return "redirect:/docs/getAllBusiness?error=110";
        }

        return "redirect:/docs/getAllBusiness";
    }

    @PostMapping("/changeBusinessName")
    public String changeBusinessName(){

        return "redirect:/docs/getAllBusiness";
    }
}
