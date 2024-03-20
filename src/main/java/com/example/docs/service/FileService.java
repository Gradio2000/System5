package com.example.docs.service;

import com.example.docs.model.Docs;
import com.example.docs.repository.DocsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zwobble.mammoth.DocumentConverter;
import org.zwobble.mammoth.Result;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Service
public class FileService {

    @Autowired
    DocsRepository docsRepository;

    public String addDoc(File file, String fullFileName, String simpleFileName, Integer businessId){
        DocumentConverter converter = new DocumentConverter();
        Result<String> result;
        String html;
        try {
            result = converter.convertToHtml(file);
            html = result.getValue(); // The generated HTML

            String simpleFileNameWithNewExtension = changeExtension(simpleFileName);
            String fullFileNameWithNewExtension = changeExtension(fullFileName);

            try (FileWriter fileWriter = new FileWriter(fullFileNameWithNewExtension, StandardCharsets.UTF_16))
            {
                fileWriter.write(html);
            }
            Docs docs = new Docs();
            docs.setFileName(simpleFileNameWithNewExtension);
            docs.setDocName(simpleFileNameWithNewExtension);
            docs.setBusinessId(businessId);

            docsRepository.save(docs);


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return html;
    }

    public static String changeExtension(String fileName) {
        int i = fileName.lastIndexOf('.');
        String name = fileName.substring(0, i);
        return name + ".html";
    }
}
