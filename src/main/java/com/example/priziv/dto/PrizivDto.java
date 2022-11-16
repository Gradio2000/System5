package com.example.priziv.dto;

import com.example.priziv.model.Priziv;
import lombok.Getter;
import lombok.Setter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
@Setter
public class PrizivDto {
    private Integer prizivId;
    private String commandName;
    private Boolean getPassports;
    private Boolean processed;
    private Integer issued;
    private Integer preparedAndNotIssued;
    private String dateArrival;
    private String dateDeparture;
    private Integer peopleAmmount;

    public PrizivDto(Integer prizivId, String commandName, Boolean getPassports,
                     Boolean processed, Integer issued, Integer preparedAndNotIssued,
                     String dateArrival, String dateDeparture, Integer peopleAmmount) {
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

    public static PrizivDto getInstance(Priziv priziv){
        return new PrizivDto(priziv.getPrizivId(), priziv.getCommandName(), priziv.getGetPassports(),
                priziv.getProcessed(), priziv.getIssued(), priziv.getPreparedAndNotIssued(),
                convetToString(priziv.getDateArrival()), convetToString(priziv.getDateDeparture()),
                priziv.getPeopleAmmount());
    }

    public static String convetToString(Date date){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        if (date != null){
            return dateFormat.format(date);
        }
        else return "";
    }
}
