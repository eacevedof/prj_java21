package com.eduardoaf.balance.shared.infrastructure.file;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.File;

import org.springframework.stereotype.Component;

@Component
public final class Log {

    public static Log getInstance() {
        return (new Log());
    }

    private void tryToCreateLogsDirectory() {
        Path path = Paths.get("logs");
        if (Files.exists(path))
            return;

        try {
            Files.createDirectory(path);
            System.out.println("Logs directory created.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void error(String errorMessage) {
        tryToCreateLogsDirectory();
        String pathFile = getPathLogWithDaySuffix("error");
        logInFile(pathFile, errorMessage, "");
    }

    public void error(String errorMessage, String title) {
        tryToCreateLogsDirectory();
        String pathFile = getPathLogWithDaySuffix("error");
        logInFile(pathFile, errorMessage, title);
    }

    public void debug(String errorMessage) {
        tryToCreateLogsDirectory();
        String pathFile = getPathLogWithDaySuffix("debug");
        logInFile(pathFile, errorMessage, "");
    }

    public void debug(String errorMessage, String title) {
        tryToCreateLogsDirectory();
        String pathFile = getPathLogWithDaySuffix("debug");
        logInFile(pathFile, errorMessage, title);
    }

    private void logInFile(String pathFile, String content, String title) {
        Path pbjPath = Paths.get(pathFile);
        try {
            tryToCreateFileOrFail();
        }
        catch (Exception e) {
            e.printStackTrace();
            return;
        }

        try (FileWriter fileWriter = new FileWriter(pbjPath.toFile(), true)) {
            var now = getNow();
            fileWriter.write("["+ now + "]: "+title+"\n" + content + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getPathLogWithDaySuffix(String fileName) {
        var logDirPath = getLogDirPath();
        return logDirPath+"/"+fileName+"_"+getTodayInYYYYmmdd()+".log";
    }

    private String getNow() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return currentDateTime.format(formatter);
    }

    private String getTodayInYYYYmmdd() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return currentDateTime.format(formatter);
    }

    private void tryToCreateFileOrFail() {
        var pathLogDir = getLogDirPath();
        File logsDir = new File(pathLogDir);
        if (logsDir.exists())
            return;
        logsDir.mkdir();
    }

    private String getLogDirPath() {
        var pathHome = getProjectDir();
        return pathHome.concat("/logs");
    }

    private String getProjectDir() {
        // $USER_HOME/projects/prj_java21
        return System.getProperty("user.dir");
    }

    public void exception(Throwable throwable) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        throwable.printStackTrace(printWriter);
        printWriter.flush();
        printWriter.close();
        var error = stringWriter.toString();
        error(error, "exception");
    }
}
