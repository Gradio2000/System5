package com.example.docs.dto;

import com.example.docs.model.Business;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BusinessDto {
    private int dtoBusinessId;
    private String dtoBusinessName;

    public static BusinessDto getInstance(Business business){
        return new BusinessDto(business.getBusinessId(), business.getBusinessName());
    }
}
