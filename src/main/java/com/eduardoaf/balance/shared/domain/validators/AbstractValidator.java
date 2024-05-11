package com.eduardoaf.balance.shared.domain.validators;

import java.util.List;

public abstract class AbstractValidator {

    protected boolean isLengthGreaterThan(String string, int maxLength) {
        return string.length() > maxLength;
    }

    protected boolean isLengthLessThan(String string, int minLength) {
        return string.length() < minLength;
    }

    protected boolean isLengthNotBetween(String string, int minLength, int maxLength) {
        return string.length() < minLength || string.length() > maxLength;
    }

    protected boolean isTypeString(String string) {
        return (string instanceof String) || isTypeNumeric(string);
    }

    protected boolean isTypeDouble(String string) {
        try {
            Double.parseDouble(string);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    protected boolean isTypeInteger(String string) {
        try {
            Integer.parseInt(string);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    protected boolean isTypeNumeric(String string) {
        return isTypeInteger(string) || isTypeDouble(string);
    }

    protected boolean isNullOrEmpty(String string) {
        return string == null || string.isEmpty() || string.isBlank();
    }

    protected boolean isTypeDate(String string) {
        return string.matches("\\d{4}-\\d{2}-\\d{2}");
    }

    protected boolean isTypeDatetime(String string) {
        return string.matches("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}");
    }

    protected boolean isTypeTime(String string) {
        return string.matches("\\d{2}:\\d{2}:\\d{2}");
    }

    protected boolean hasInvalidCharacters(String string, List<String> invalidCharacters) {
        for (String invalidCharacter : invalidCharacters) {
            if (string.contains(invalidCharacter)) {
                return true;
            }
        }
        return false;
    }

    protected boolean isNotInList(String string, List<String> validValues) {
        return !validValues.contains(string);
    }

    protected boolean isNotInList(int integer, List<Integer> validValues) {
        return !validValues.contains(integer);
    }

    protected boolean isTypeList(Object object) {
        return object instanceof List;
    }
}
