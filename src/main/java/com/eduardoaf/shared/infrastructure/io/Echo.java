package com.eduardoaf.shared.infrastructure.io;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public final class Echo {

    public static Echo getInstance() {
        return (new Echo());
    }

    public void info(String message) {
        printWithTime(message, "info");
    }

    public void error(String message) {
        printWithTime(message, "error");
    }

    private String getNow() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return currentDateTime.format(formatter);
    }

    private void printWithTime(String message, String title) {
        String now = getNow();
        System.out.println("["+ now +"]["+title+"]:\n"+ message);
    }
}
