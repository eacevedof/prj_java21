package com.eduardoaf.balance.shared.infrastructure.formatters;

import org.springframework.stereotype.Component;

@Component
public final class StringFormatter {

    public static StringFormatter getInstance() {
        return new StringFormatter();
    }

    public String getTrimOrNull(String anyString) {
        if (anyString == null)
            return null;
        if (anyString.isEmpty() || anyString.isBlank())
            return "";
        return anyString.trim();
    }
}
