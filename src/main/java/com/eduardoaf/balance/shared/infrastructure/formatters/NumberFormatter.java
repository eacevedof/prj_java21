package com.eduardoaf.balance.shared.infrastructure.formatters;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.text.DecimalFormat;

@Component
public final class NumberFormatter {

    public static NumberFormatter getInstance() {
        return new NumberFormatter();
    }

    public Double getAsDouble(String number) {
        return Double.parseDouble(number);
    }

    public Integer getNull() {
        return null;
    }

    public Double getAsDouble3dec(Double number) {
        // 99234567.123 decimal(12,3
        DecimalFormat decFormatter = new DecimalFormat("#.###");
        String strDouble3 = decFormatter.format(number);
        strDouble3 = strDouble3.replace(",", ".");
        return Double.parseDouble(strDouble3);
    }

    public Integer getAsInteger(String number) {
        if (number == null)
            return null;
        if (number.isEmpty())
            return null;
        return Integer.getInteger(number);
    }

    public String getAsString(Double doubleNum) {
        return doubleNum.toString();
    }

    public String getAsString(Integer intNum) {
        return intNum.toString();
    }
}
