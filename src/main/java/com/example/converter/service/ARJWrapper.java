package com.example.converter.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ARJWrapper {
    public List<String> extract(String arjFile) {
        List<String> output = new ArrayList<>();
        try {
            String[] command = {"/Users/aleksejlaskin/Library/Application Support/CrossOver/Bottles/arj/drive_c/ARJ32/ARJ32.EXE", "x", arjFile};
//            String command = "/Users/aleksejlaskin/Library/Application Support/CrossOver/Bottles/arj/drive_c/ARJ32/ARJ32.EXE x " + arjFile;
            ProcessBuilder processBuilder = new ProcessBuilder(command);
            Process process = processBuilder.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                output.add(line);
            }

            int exitCode = process.waitFor();
            System.out.println("Завершено с кодом: " + exitCode);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return output;
    }

    public static void main(String[] args) {
        ARJWrapper wrapper = new ARJWrapper();
        List<String> output = wrapper.extract("/Users/aleksejlaskin/Desktop/2zk02_01.ccc");
        for (String line : output) {
            System.out.println(line);
        }
    }
}
