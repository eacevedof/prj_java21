package com.eduardoaf.balance.shared.infrastructure.formatters;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Component
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
