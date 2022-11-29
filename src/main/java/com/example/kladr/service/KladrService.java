package com.example.kladr.service;

import com.example.kladr.dto.HouseDto;
import com.example.kladr.model.House;
import com.example.kladr.model.KladrAll;
import com.example.kladr.repository.KladrAllRepository;

import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Service
public class KladrService {
    private final KladrAllRepository kladrAllRepository;

    public KladrService(KladrAllRepository kladrAllRepository) {
        this.kladrAllRepository = kladrAllRepository;
    }

    public List<HouseDto> getHouseDtoList(List<House> houseList){
        List<HouseDto> houseDtoList = new ArrayList<>();
        int id = 1;
        for (House house: houseList){
            String[] houseMass = house.getName().split(",");
            for (String mass : houseMass) {
                HouseDto houseDto = HouseDto.getInstance(id, mass, house.getIndex());
                houseDtoList.add(houseDto);
                id++;
            }
        }
        return houseDtoList;
    }

    public List<KladrAll> getKladrAllRepository(String value){
        String[] mass = value.split(" ");
        String value1 = "";
        String value2 = "";
        String value3 = "";
        if (mass.length == 1){
            return kladrAllRepository.selectAll(mass[0]);
        }
        if (mass.length == 2){
            value1 = mass[0];
            value2 = mass[1];
            return kladrAllRepository.selectAll(value1, value2);
        }
        if (mass.length == 3){
            value1 = mass[0];
            value2 = mass[1];
            value3 = mass[2];
            return kladrAllRepository.selectAll(value1, value2, value3);
        }
        else {
            return null;
        }
    }
}
