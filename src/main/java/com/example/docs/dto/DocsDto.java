package com.example.docs.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DocsDto {

    private Integer docId;
    private String docName;
    private String fileName;
    private String regNumber;
    private Integer businessId;

}
