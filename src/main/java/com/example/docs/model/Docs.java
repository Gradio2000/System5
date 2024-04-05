package com.example.docs.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "d_docs")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Docs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "doc_id")
    private Integer docId;

    @Column (name = "doc_name")
    private String docName;

    @Column (name = "file_name")
    private String fileName;

    @Column (name = "reg_number")
    private String regNumber;

    @Column(name = "business_id")
    private Integer businessId;

    @Column(name = "text")
    private String text;

}
