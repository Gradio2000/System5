package com.example.fnsConverter.service;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Set;

@Service
public class Converters {

    public String sumConvert(String sumPlat){
        return sumPlat.substring(0, sumPlat.length() - 2) + "-" + sumPlat.substring(sumPlat.length() - 2);
    }

    public String dateConvert(String olddate){
        LocalDate date = LocalDate.parse(olddate);
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return df.format(date);
    }


    public void deleteCharsFromValues(Map<String, String> map){
        Set<String> keySet = map.keySet();
        for (String key : keySet){
            String newValue = map.get(key).replace(", ", " ");
            map.put(key, newValue);
        }
    }

    public String convertMapToString(Map<String, String> resultMap){
        String stringTo = resultMap.toString();

        stringTo = stringTo.replace("=", ":");
        stringTo = stringTo.replace("{", "");
        stringTo = stringTo.replace("}", "");

        return stringTo;
    }
}
