package shared.infrastructure.file;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;

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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void logError(String errorMessage) {
        tryToCreateLogsDirectory();
        String pathFile = getPathLogWithDaySuffix("error");
        logInFile(pathFile, errorMessage, "");
    }

    public void logError(String errorMessage, String title) {
        tryToCreateLogsDirectory();
        String pathFile = getPathLogWithDaySuffix("error");
        logInFile(pathFile, errorMessage, title);
    }

    public void logDebug(String errorMessage) {
        tryToCreateLogsDirectory();
        String pathFile = getPathLogWithDaySuffix("debug");
        logInFile(pathFile, errorMessage, "");
    }

    public void logDebug(String errorMessage, String title) {
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

    private void tryToCreateFileOrFail() throws Exception {
        var pathLogDir = getLogDirPath();
        File logsDir = new File(pathLogDir);
        if (logsDir.exists())
            return;
        logsDir.mkdir();
    }

    private String getLogDirPath() {
        //var pathHome = System.getenv(EnvVarEnum.PATH_PROJECTS.getValue());
        var pathHome = getProjectDir();
        return pathHome.concat("/logs");
    }

    private String getProjectDir() {
        // $USER_HOME/projects/prj_java21
        return System.getProperty("user.dir");
    }

    public void logException(Throwable throwable) {
        // Create a StringWriter to capture the stack trace
        StringWriter stringWriter = new StringWriter();
        // Create a PrintWriter to write the stack trace to the StringWriter
        PrintWriter printWriter = new PrintWriter(stringWriter);
        // Write the stack trace to the PrintWriter
        throwable.printStackTrace(printWriter);
        // Flush the PrintWriter to ensure everything is written
        printWriter.flush();
        // Close the PrintWriter
        printWriter.close();
        // Return the stack trace as a string
        var error = stringWriter.toString();
        logError(error, "exception");
    }
}
