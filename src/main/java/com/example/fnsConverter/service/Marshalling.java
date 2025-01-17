package com.example.fnsConverter.service;

import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;

@Service
public class Marshalling {


    public Object unMarshalling(String path, Class<?> clazz){
        BufferedReader br;
        try {
            br = new BufferedReader(new InputStreamReader(Files.newInputStream(Path.of(path)), "WINDOWS-1251"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String body = br.lines().collect(Collectors.joining());
        StringReader reader = new StringReader(body);
        JAXBContext context;
        try {
            context = JAXBContext.newInstance(clazz);
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
        Unmarshaller unmarshaller;
        try {
            unmarshaller = context.createUnmarshaller();
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
        try {
            return  unmarshaller.unmarshal(reader);
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

}
