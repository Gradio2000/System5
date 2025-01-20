package com.example.fnsConverter.service;


import com.example.fnsConverter.model.sff.RootTagSFF;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class SFFService {

    @Autowired
    private Marshalling marshalling;

    public List<RootTagSFF> createSFF(String pathToFolder){
        log.info(new Object(){}.getClass().getEnclosingMethod().getName());

        Path directoryPath = Paths.get(pathToFolder);
        List<RootTagSFF> rootTagSFFS = new ArrayList<>();
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(directoryPath)) {
            RootTagSFF rootTagSFF;
            for (Path entry : stream) {
                if (Files.isRegularFile(entry)) {
                   rootTagSFF = (RootTagSFF) marshalling.unMarshalling(String.valueOf(entry), RootTagSFF.class);
                   rootTagSFFS.add(rootTagSFF);
                } else if (Files.isDirectory(entry)) {
                    System.out.println("Directory: " + entry);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        log.info("rootTagSFFS size " + rootTagSFFS.size());
        return rootTagSFFS;
    }

    public List<Map<String, String>> getSFFListMaps(List<RootTagSFF> rootTagSFFList){
        List<Map<String, String>> mapList = new ArrayList<>();
        for (RootTagSFF rootTagSFF: rootTagSFFList){
            Map<String, String> stringMap = new LinkedHashMap<>();
            stringMap.put("Идентификатор файла", rootTagSFF.getIdFail());
            stringMap.put("Тип информации", rootTagSFF.getTipInf());
            stringMap.put("Версия передающей программы ", rootTagSFF.getVersPrrog());
            stringMap.put("Телефон отправителя", rootTagSFF.getTelOtpr());
            stringMap.put("Должность отправителя", rootTagSFF.getDoljnOtpr());
            stringMap.put("Фамилия отправителя ", rootTagSFF.getFamOtpr());
            stringMap.put("Количество документов", rootTagSFF.getKolDoc());
            stringMap.put("Версия формата", rootTagSFF.getVersForm());


            stringMap.put("Идентификатор документа ", rootTagSFF.getSff().getIdDoc());
            stringMap.put("Дата обработки сообщения банка", rootTagSFF.getSff().getDataObr());
            stringMap.put("Идентификатор файла сообщения банка", rootTagSFF.getSff().getIdFailIsh());
            stringMap.put("Номер сообщения банка", rootTagSFF.getSff().getNomSoob());
            stringMap.put("Тип сообщения банка", rootTagSFF.getSff().getTipSoob());
            stringMap.put("Дата сообщения банком сведений в налоговый орган", rootTagSFF.getSff().getDataSoob());
            stringMap.put("Код обработки", rootTagSFF.getSff().getKodObr());
            stringMap.put("Результат обработки", rootTagSFF.getSff().getRezObr());


            mapList.add(stringMap);
        }
        return mapList;
    }
}
