package com.eduardoaf.balance.mod_shared.infrastructure.formatters;

import org.springframework.stereotype.Component;

@Component
public final class StringFormatter {

    public static StringFormatter getInstance() {
        return new StringFormatter();
    }

    public String getNullIfEmpty(String anyString) {
        if (anyString == null)
            return null;
        if (anyString.isEmpty() || anyString.isBlank())
            return null;
        return anyString;
    }

    public String getNull() {
        return null;
    }

    public String getTrimOrNull(String anyString) {
        if (anyString == null)
            return null;
        if (anyString.isEmpty() || anyString.isBlank())
            return "";
        return anyString.trim();
    }

    public String getTrimOrDefault(String anyString, String defaultValue) {
        if (anyString == null)
            return defaultValue;
        if (anyString.isEmpty() || anyString.isBlank())
            return defaultValue;
        return anyString.trim();
    }

    public String getAlwaysString(String anyString) {
        if (anyString == null)
            return "";
        if (anyString.isEmpty() || anyString.isBlank())
            return "";
        return anyString.trim();
    }

    public String getAlwaysString(Integer number) {
        if (number == null)
            return "";
        return number.toString();
    }

    public Boolean getBoolean(String number) {
        if (number == null)
            return false;
        return !number.isEmpty();
    }
}
