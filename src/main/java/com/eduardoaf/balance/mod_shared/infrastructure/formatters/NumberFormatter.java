package com.eduardoaf.balance.mod_shared.infrastructure.formatters;

import org.springframework.stereotype.Component;

import java.text.DecimalFormat;

@Component
public final class NumberFormatter {

    public static NumberFormatter getInstance() {
        return new NumberFormatter();
    }

    public Double getDoubleOrNull(String number) {
        if (number == null)
            return null;
        if (number.isEmpty())
            return null;
        return Double.parseDouble(number);
    }

    public Double getDoubleOrDefault(String number, Double defaultValue) {
        if (number == null)
            return defaultValue;
        if (number.isEmpty())
            return defaultValue;
        return Double.parseDouble(number);
    }

    public Integer getNull() {
        return null;
    }

    public Double getDouble3dec(Double number) {
        if (number == null)
            return null;
        // 99234567.123 decimal(12,3
        DecimalFormat decFormatter = new DecimalFormat("#.###");
        String strDouble3 = decFormatter.format(number);
        strDouble3 = strDouble3.replace(",", ".");
        return Double.parseDouble(strDouble3);
    }

    public Double getDouble3dec(String number) {
        if (number == null)
            return null;
        if (number.isEmpty())
            return null;
        return getDouble3dec(Double.parseDouble(number));
    }

    public Integer getIntegerOrNull(String number) {
        if (number == null)
            return null;
        if (number.isEmpty())
            return null;
        return Integer.parseInt(number);
    }

    public Boolean getBoolean(String number) {
        if (number == null)
            return false;
        if (number.isEmpty())
            return false;
        return Integer.parseInt(number) > 0;
    }

    public Integer getIntegerOrDefault(String number, Integer defaultValue) {
        if (number == null)
            return defaultValue;
        if (number.isEmpty())
            return defaultValue;
        return Integer.parseInt(number);
    }

    public String getAsString(Double doubleNum) {
        return doubleNum.toString();
    }

    public String getAsString(Integer intNum) {
        return intNum.toString();
    }
}
