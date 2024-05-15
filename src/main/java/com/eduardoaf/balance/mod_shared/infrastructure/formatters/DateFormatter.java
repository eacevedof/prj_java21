package com.eduardoaf.balance.mod_shared.infrastructure.formatters;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public final class DateFormatter {

    public static DateFormatter getInstance() {
        return new DateFormatter();
    }

    public String getNow() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        // Define a formatter for the desired format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        // Format the date and time into a string
        return currentDateTime.format(formatter);
    }
}
