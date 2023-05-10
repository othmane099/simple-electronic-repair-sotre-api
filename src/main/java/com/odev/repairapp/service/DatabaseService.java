package com.odev.repairapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@RequiredArgsConstructor
@Service
public final class DatabaseService {

    @Value("${spring.datasource.username}")
    private String dbUsername;
    @Value("${spring.datasource.password}")
    private String dbPassword;
    @Value("${spring.datasource.name}")
    private String dbName;


    public void backup()
            throws IOException, InterruptedException {
        String command = String.format("mysqldump -u%s -p%s --add-drop-table --databases %s -r %s",
                dbUsername, dbPassword, dbName, System.currentTimeMillis()+".sql");
        Process process = Runtime.getRuntime().exec(command);
        int processComplete = process.waitFor();
    }


    public boolean restore(String sourceFile)
            throws IOException, InterruptedException {
        String[] command = new String[]{
                "mysql",
                "-u" + dbUsername,
                "-p" + dbPassword,
                "-e",
                " source " + sourceFile,
                dbName
        };
        Process runtimeProcess = Runtime.getRuntime().exec(command);
        int processComplete = runtimeProcess.waitFor();
        return processComplete == 0;
    }
}

