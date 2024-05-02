package shared.infrastructure.io;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class Echo {

    public static Echo getInstance() {
        return (new Echo());
    }

    public static void info(String message) {
        printWithTime(message, "info");
    }

    public static void info(String message, String title) {
        printWithTime(title+": "+message, "info");
    }

    public static void error(String message) {
        printWithTime(message, "error");
    }

    private static String getNow() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return currentDateTime.format(formatter);
    }

    private static void printWithTime(String message, String title) {
        String now = getNow();
        System.out.println("["+ now +"]["+title+"]:\n"+ message);
    }
}
