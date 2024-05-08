package com.example.docs.dto;

import com.example.docs.model.Business;
import com.example.docs.model.Docs;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class BusinessDto {
    private int dtoBusinessId;
    private String dtoBusinessName;
    private List<DocsDto> docsDtoList;

    public BusinessDto(int dtoBusinessId, String dtoBusinessName) {
        this.dtoBusinessId = dtoBusinessId;
        this.dtoBusinessName = dtoBusinessName;
    }

    public BusinessDto(int dtoBusinessId, String dtoBusinessName, List<Docs> docsList) {
        this.dtoBusinessId = dtoBusinessId;
        this.dtoBusinessName = dtoBusinessName;
        this.docsDtoList = convertToDto(docsList);
    }

    public static BusinessDto getInstance(Business business){
        return new BusinessDto(business.getBusinessId(), business.getBusinessName());
    }

    public List<DocsDto> convertToDto(List<Docs> docsList){
        return docsList.stream()
                .map(DocsDto::new)
                .collect(Collectors.toList());
    }
}
