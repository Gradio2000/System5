package com.example.priziv.dto;

import com.example.priziv.model.PrizivMonthYear;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PrizivMonthYearDto {
    private int id;
    private String monthYearName;

    public PrizivMonthYearDto(int id, String monthYearName) {
        this.id = id;
        this.monthYearName = monthYearName;
    }

    public static PrizivMonthYearDto getInstance(PrizivMonthYear prizivMonthYear){
        return new PrizivMonthYearDto(prizivMonthYear.getId(), prizivMonthYear.getMonthYearName());
    }
}
