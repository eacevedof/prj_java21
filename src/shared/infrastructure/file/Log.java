package shared.infrastructure.file;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class Log {

    public static Log getInstance() {
        return (new Log());
    }

    private void createLogsDirectory() {
        Path path = Paths.get("logs");
        if (Files.exists(path))
            return;
        try {
            Files.createDirectory(path);
            System.out.println("Logs directory created.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void logError(String errorMessage) {
        createLogsDirectory();
        String pathFile = getPathLogWithDaySuffix("error");
        logInFile(pathFile, errorMessage, "");
    }

    public void logError(String errorMessage, String title) {
        createLogsDirectory();
        String pathFile = getPathLogWithDaySuffix("error");
        logInFile(pathFile, errorMessage, title);
    }

    public void logDebug(String errorMessage) {
        createLogsDirectory();
        String pathFile = getPathLogWithDaySuffix("debug");
        logInFile(pathFile, errorMessage, "");
    }

    public void logDebug(String errorMessage, String title) {
        createLogsDirectory();
        String pathFile = getPathLogWithDaySuffix("debug");
        logInFile(pathFile, errorMessage, title);
    }

    private void logInFile(String pathFile, String content, String title) {
        Path logFile = Paths.get(pathFile);
        try (FileWriter fileWriter = new FileWriter(logFile.toFile(), true)) {
            var now = getNow();
            fileWriter.write("["+ now + "]:"+title+"\n" + content + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getPathLogWithDaySuffix(String fileName) {
        return "../logs/"+fileName+"_"+getTodayInYYYYmmdd()+".log";
    }

    private String getNow() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        // Define a formatter for the desired format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        // Format the date and time into a string
        return currentDateTime.format(formatter);
    }

    private String getTodayInYYYYmmdd() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return currentDateTime.format(formatter);
    }
}
