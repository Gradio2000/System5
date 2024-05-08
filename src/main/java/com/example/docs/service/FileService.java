package com.example.docs.service;

import com.example.docs.model.Docs;
import fr.opensagres.poi.xwpf.converter.xhtml.XHTMLConverter;
import fr.opensagres.poi.xwpf.converter.xhtml.XHTMLOptions;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.tika.Tika;
import org.apache.tika.exception.TikaException;
import org.springframework.stereotype.Service;

import java.io.*;

@Service
public class FileService {

    public static String changeExtension(String fileName) {
        int i = fileName.lastIndexOf('.');
        String name = fileName.substring(0, i);
        return name + ".html";
    }

    public String getTxtFile(File file){
        Tika tika = new Tika();
        try {
            return tika.parseToString(file);
        } catch (IOException | TikaException e) {
            throw new RuntimeException(e);
        }
    }


    public String saveHtmlFile(File file, String fullFileName){
        String fullFileNameWithNewExtension = changeExtension(fullFileName);

        try(InputStream docxInputStream = new FileInputStream(file);
            OutputStream pdfOutputStream = new FileOutputStream(fullFileNameWithNewExtension)) {
            XWPFDocument document = new XWPFDocument(docxInputStream);
            XHTMLOptions options = XHTMLOptions.create();
            // Convert .docx file to .html file
            XHTMLConverter.getInstance().convert(document, pdfOutputStream, options);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fullFileNameWithNewExtension;
    }

    public String getHtmlFile(String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
            try {
                BufferedReader reader = new BufferedReader(new FileReader(fileName));
                String line = reader.readLine();
                stringBuilder.append(line);
                while (reader.readLine() != null) {
                    line = reader.readLine();
                    stringBuilder.append(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        return stringBuilder.toString();
    }

    public Docs createDocsInstance(File newFile, String simpleFileName, Integer businessId){
        String simpleFileNameWithNewExtension = changeExtension(simpleFileName);
        Docs docs = new Docs();
        docs.setFileName(simpleFileNameWithNewExtension);
        docs.setDocName(simpleFileNameWithNewExtension);
        docs.setBusinessId(businessId);
        docs.setText(getTxtFile(newFile));
        return docs;
    }
}

