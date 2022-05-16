package com.example.studentdb;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class LogFile {
    private static LogFile instance;
    private File logs;
    private FileWriter writer;
    public static LogFile getInstance(){
        if (instance ==null)
            instance = new LogFile();
        return instance;
    }
    private LogFile(){
        logs = new File("src/main/java/com/example/studentdb/logs");

    }

    public void logOperation(String operation){
        try {
            writer = new FileWriter(logs,true);
            writer.append(operation);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
