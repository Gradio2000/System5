package com.example.priziv.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "p_priziv")
@Getter
@Setter
public class Priziv {
    @Id
    @Column(name = "priziv_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer prizivId;

    @Column(name = "command_name")
    private String commandName;

    @Column(name = "get_passports")
    private Boolean getPassports;

    @Column(name = "processed")
    private Boolean processed;

    @Column(name = "issued")
    private Integer issued;

    @Column(name = "prepared_and_not_issued")
    private Integer preparedAndNotIssued;

    @Column(name = "date_arrival")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateArrival;

    @Column(name = "date_departure")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateDeparture;

    @Column(name = "people_ammount")
    private Integer peopleAmmount;
}
