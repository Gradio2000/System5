package com.example.qtest.service;

import com.example.qtest.controller.utils.UtilsMethods;
import com.example.qtest.model.Question;
import com.example.qtest.model.Test;
import com.example.qtest.repository.TestReposytory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;

@Service
public class FileUploadService {
    private final TestReposytory testReposytory;
    private final UtilsMethods utilsMethods;

    public FileUploadService(TestReposytory testReposytory, UtilsMethods utilsMethods) {
        this.testReposytory = testReposytory;
        this.utilsMethods = utilsMethods;
    }

    public void uploadQuestionFromFile(MultipartFile file, Integer testId) throws IOException, ArrayIndexOutOfBoundsException {
        File newFile = File.createTempFile("temp", null, null);
        file.transferTo(newFile);

        List<Question> questionList = utilsMethods.parserFile(newFile, testId);
        utilsMethods.setManyChooseProperty(questionList);

        Test test = testReposytory.findById(testId).orElse(null);
        assert test != null;
        test.setQuestions(new HashSet<>(questionList));
        testReposytory.save(test);
    }
}
