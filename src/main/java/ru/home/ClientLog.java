package ru.home;

import au.com.bytecode.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ClientLog {
    private static final char EXPORT_FILE_SEPARATOR = ',';
    private static final String[] EXPORT_LOG_FILE_TITLE = {"ProductNum", "Amount"};

    private final List<String[]> clientLog = new ArrayList<>();

    public void log(int productNum, int amount) {
        clientLog.add(
                new String[]{
                        Integer.toString(productNum),
                        Integer.toString(amount)
                });
    }

    public void exportAsCSV(File filename) {
        try (CSVWriter writer = new CSVWriter(new FileWriter(filename), EXPORT_FILE_SEPARATOR)) {
            writer.writeNext(EXPORT_LOG_FILE_TITLE);
            writer.writeAll(clientLog);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
