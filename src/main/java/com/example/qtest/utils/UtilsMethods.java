package com.example.qtest.utils;

import com.example.qtest.model.Answer;
import com.example.qtest.model.Question;
import com.example.system5.util.AuthUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class UtilsMethods {

    public void setManyChooseProperty(List<Question> questionList){
        questionList.forEach(ques -> {
            ques.setManyChoose(checkAndSetManyChooseParameter(ques.getAnswers()));
        });
    }

    public boolean checkAndSetManyChooseParameter(List<Answer> answers){
        return answers.stream()
                .filter(Answer::getIsRight)
                .count() > 1;
    }

    public List<Question> parserFile(File newFile, Integer testId){

        AuthUser authUser = (AuthUser) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();


        log.info(new Object(){}.getClass().getEnclosingMethod().getName() + " " +
                authUser.getUser().getName());

        BufferedReader reader;
        List<Question> questionList = new ArrayList<>();
        try {
            reader = new BufferedReader(new InputStreamReader(Files.newInputStream(newFile.toPath())));
            String line;
            Question question;
            Answer answer;
            List<Answer> answerList;

            int i = 0;
            while ((line = reader.readLine()) != null) {
                String[] lineMass = line.split(";");

                //проверяем первую ячейку
                if (!lineMass[0].equals("")){
                    question = new Question();
                    question.setQuestionName(lineMass[0]);
                    answerList = new ArrayList<>();
                    i++;
                }
                else {
                    if (questionList.size() != 0){
                        question = questionList.remove(i - 1);
                        answerList = question.getAnswers();
                    }
                    else {
                        question = new Question();
                        answerList = new ArrayList<>();
                    }
                }

                answer = new Answer();

                //проверяем вторую ячейку
                if (!lineMass[1].equals("")){
                    answer.setAnswerName(lineMass[1]);
                }

                //проверяем третью ячейку
                if (lineMass.length == 3) {
                    answer.setIsRight(true);
                }

                answer.setQuestion(question);
                answerList.add(answer);

                question.setAnswers(answerList);
                question.setTestId(testId);
                question.setDeleted(false);
                question.setImageRef(null);
                question.setPromt(null);
                question.setManyChoose(false);

                questionList.add(question);
            }
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException("ощибка парсинга файла" + e);
        }
        return questionList;
    }
}
