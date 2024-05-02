package shared.infrastructure.formatters;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class Date {

    public static Date getInstance() {
        return new Date();
    }

    public String getNow() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        // Define a formatter for the desired format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        // Format the date and time into a string
        return currentDateTime.format(formatter);
    }
}
