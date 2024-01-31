package com.example.qtest.service;

import com.example.qtest.dto.AppointTestDto;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.List;

public class ImportExcel {
    private static String filename;

    public static String getFilename() {
        return filename;
    }

    public static ResponseEntity<InputStreamResource> downloadFile() throws IOException {
        File file = new File(ImportExcel.getFilename());
        InputStreamResource resource = new InputStreamResource(Files.newInputStream(file.toPath()));
        return ResponseEntity.ok()
                // Content-Disposition
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getName())
                // Content-Type
                .contentType(MediaType.TEXT_PLAIN)
                // Contet-Length
                .contentLength(file.length()) //
                .body(resource);
    }

    public static void importToExcel(List<AppointTestDto> appointTestDtoList) throws IOException {

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("report");

        int rownum = 0;
        Cell cell;
        Row row;

        row = sheet.createRow(rownum);

        cell = row.createCell(0, CellType.STRING);
        cell.setCellValue("Дата и номер распорядительного акта ПУ (письма ДПУ), являющегося основанием для проведения зачета");

        cell = row.createCell(1, CellType.STRING);
        cell.setCellValue("Формат и дата проведения зачета");

        cell = row.createCell(2, CellType.STRING);
        cell.setCellValue("Фамилия, инициалы и должность лица, сдававшего зачет");

        cell = row.createCell(3, CellType.STRING);
        cell.setCellValue("Сумма набранных баллов");

        cell = row.createCell(4, CellType.STRING);
        cell.setCellValue("Решение комиссии");


        for (AppointTestDto appointTestDto : appointTestDtoList){
            rownum++;
            row = sheet.createRow(rownum);

            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue(appointTestDto.getBase());

            cell = row.createCell(1, CellType.STRING);
            SimpleDateFormat formatForDateNow = new SimpleDateFormat("dd.MM.yyyy");
            cell.setCellValue("очное тестирование " + formatForDateNow.format(appointTestDto.getAttempttestDto().getDateTime()));

            cell = row.createCell(2, CellType.STRING);
            cell.setCellValue(appointTestDto.getUserDtoNameOnlyWithPositionDto().getName() + " " +
                    appointTestDto.getUserDtoNameOnlyWithPositionDto().getPositionDtoNameOnly().getPosition());

            cell = row.createCell(3, CellType.STRING);
            cell.setCellValue(appointTestDto.getAttempttestDto().getAmountTrueAnswers() +
                    " из " + appointTestDto.getAttempttestDto().getAmountQues());

            cell = row.createCell(4, CellType.STRING);
            cell.setCellValue(appointTestDto.getAttempttestDto().getTestResult());


        }

        File file = File.createTempFile("report",".xlsx", null);
        FileOutputStream outFile = new FileOutputStream(file);
        workbook.write(outFile);
        outFile.close();
        filename = file.getPath();

    }


    public static void importToExcelBaseJournal(List<AppointTestDto> appointTestDtoList) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("report");

        int rownum = 0;
        Cell cell;
        Row row;

        row = sheet.createRow(rownum);

        cell = row.createCell(0, CellType.STRING);
        cell.setCellValue("Дата и время");

        cell = row.createCell(1, CellType.STRING);
        cell.setCellValue("Работник");

        cell = row.createCell(2, CellType.STRING);
        cell.setCellValue("Название теста");

        cell = row.createCell(3, CellType.STRING);
        cell.setCellValue("Результат");


        for (AppointTestDto appointTestDto : appointTestDtoList){
            rownum++;
            row = sheet.createRow(rownum);

            cell = row.createCell(0, CellType.STRING);
            SimpleDateFormat formatForDateNow = new SimpleDateFormat("dd.MM.yyyy hh:mm");
            cell.setCellValue(formatForDateNow.format(appointTestDto.getAttempttestDto().getDateTime()));

            cell = row.createCell(1, CellType.STRING);
            cell.setCellValue(appointTestDto.getUserDtoNameOnlyWithPositionDto().getName() + " " +
                    appointTestDto.getUserDtoNameOnlyWithPositionDto().getPositionDtoNameOnly().getPosition());

            cell = row.createCell(2, CellType.STRING);
            cell.setCellValue(appointTestDto.getAttempttestDto().getTestName());

            cell = row.createCell(3, CellType.STRING);
            cell.setCellValue(appointTestDto.getAttempttestDto().getTestResult());


        }

        File file = File.createTempFile("report",".xlsx", null);
        FileOutputStream outFile = new FileOutputStream(file);
        workbook.write(outFile);
        outFile.close();
        filename = file.getPath();
    }
}
