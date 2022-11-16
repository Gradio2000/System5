package com.example.priziv.model;

import com.example.priziv.Dto.PrizivDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    private Date dateArrival;

    @Column(name = "date_departure")
    private Date dateDeparture;

    @Column(name = "people_ammount")
    private Integer peopleAmmount;

    public Priziv() {
    }

    public Priziv(Integer prizivId, String commandName, Boolean getPassports, Boolean processed,
                  Integer issued, Integer preparedAndNotIssued, Date dateArrival, Date dateDeparture,
                  Integer peopleAmmount) {
        this.prizivId = prizivId;
        this.commandName = commandName;
        this.getPassports = getPassports;
        this.processed = processed;
        this.issued = issued;
        this.preparedAndNotIssued = preparedAndNotIssued;
        this.dateArrival = dateArrival;
        this.dateDeparture = dateDeparture;
        this.peopleAmmount = peopleAmmount;
    }

    public static Priziv getInstance(PrizivDto prizivDto){
        return new Priziv(prizivDto.getPrizivId(), prizivDto.getCommandName(), prizivDto.getGetPassports(),
                 prizivDto.getProcessed(), prizivDto.getIssued(), prizivDto.getPreparedAndNotIssued(),
                convertStringToDate(prizivDto.getDateArrival()), convertStringToDate(prizivDto.getDateDeparture()),
                prizivDto.getPeopleAmmount());
    }

    private static Date convertStringToDate(String str){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date;
        try {
            if (str.equals("")){
                date = null;
            }else {
                date = formatter.parse(str);
            }

        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return date;
    }

}
