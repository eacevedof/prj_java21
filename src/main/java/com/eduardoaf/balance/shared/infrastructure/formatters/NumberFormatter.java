package com.eduardoaf.balance.shared.infrastructure.formatters;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public final class NumberFormatter {

    public static NumberFormatter getInstance() {
        return new NumberFormatter();
    }

    public Double getAsDouble(String number) {
        return Double.parseDouble(number);
    }

    public Integer getAsInteger(String number) {
        return Integer.getInteger(number);
    }

    public String getAsString(Double doubleNum) {
        return doubleNum.toString();
    }

    public String getAsString(Integer intNum) {
        return intNum.toString();
    }
}
