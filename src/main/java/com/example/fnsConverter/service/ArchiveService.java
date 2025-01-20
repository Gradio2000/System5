package com.example.fnsConverter.service;


import com.github.junrar.Archive;
import com.github.junrar.exception.RarException;
import com.github.junrar.rarfile.FileHeader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

@Service
@Slf4j
public class ArchiveService {

    public String unpuckArhive(MultipartFile file) {
        log.info(new Object(){}.getClass().getEnclosingMethod().getName());

        File newFile;
        String destinationPath;
        try {
            newFile = File.createTempFile("temp", null, null);
            file.transferTo(newFile);
            Archive archive = new Archive(newFile.getAbsoluteFile());
            FileHeader fileHeader = archive.nextFileHeader();

            destinationPath = createTempDirectory();

            while (fileHeader != null) {
                if (!fileHeader.isDirectory()) {

                    String fileName = fileHeader.getFileNameString();
                    // Remove the directory part from the file name
                    String baseFileName = getFileName(fileName);
                    File fileFromArchive = new File(destinationPath, baseFileName);
                    if (!fileFromArchive.getParentFile().exists()) {
                        fileFromArchive.getParentFile().mkdirs();
                    }

                    FileOutputStream fos = new FileOutputStream(fileFromArchive);
                    archive.extractFile(fileHeader, fos);
                    fos.close();
                }
                fileHeader = archive.nextFileHeader();
            }
            archive.close();
        } catch (IOException | RarException e) {
            throw new RuntimeException(e);
        }

        log.info("destinationPath " + destinationPath);
        log.info("newFile.toPath() " + newFile.toPath());

        return destinationPath;
    }



    public String createTempDirectory() {
        Path tempDir = null;
        try {
            // Создаем временную папку
            tempDir = Files.createTempDirectory("tempDirPrefix");
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert tempDir != null;
        return tempDir.toString();
    }

    public void deleteTempDirectory(String tempDir){
        try {
            Files.walkFileTree(Path.of(tempDir), new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    Files.delete(file);
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                    Files.delete(dir);
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getFileName(String fullName){
        int index = fullName.lastIndexOf("\\");
//        if (index < prefixLength) return path.substring(prefixLength);
        return fullName.substring(index + 1);
    }




}
