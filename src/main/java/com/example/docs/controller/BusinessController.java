package com.example.docs.controller;

import com.example.docs.dto.BusinessDto;
import com.example.docs.model.Business;
import com.example.docs.repository.BusinessRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/docs")
public class BusinessController {

   private final BusinessRepository businessRepository;

    public BusinessController(BusinessRepository businessRepository) {
        this.businessRepository = businessRepository;
    }

    @GetMapping("/getAllBuiseness")
    public String getAllBusiness(Model model){
        List<Business> businessList = businessRepository.findAll();
        List<BusinessDto> businessDtoList = businessList.stream()
                .map(BusinessDto::getInstance)
                .collect(Collectors.toList());
        model.addAttribute(businessDtoList);
        return "docs/allbusiness";
    }

    @GetMapping("/getBusinessById/{businessDtoId}")
    public String getBusinessById(@PathVariable Integer businessDtoId, Model model){
        Business business = businessRepository.findById(businessDtoId).orElse(null);
        assert business != null;
        model.addAttribute(business);
        return "docs/business";
    }

}
