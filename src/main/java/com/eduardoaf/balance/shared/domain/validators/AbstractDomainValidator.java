package com.eduardoaf.balance.shared.domain.validators;

import java.util.List;

public abstract class AbstractDomainValidator {

    protected boolean isValueEmail(String strEmail) {
        return strEmail.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$");
    }

    protected boolean isValueLenGreaterThan(String strValue, int maxLength) {
        return strValue.length() > maxLength;
    }

    protected boolean isValueLenLighterThan(String strValue, int minLength) {
        return strValue.length() < minLength;
    }

    protected boolean isValueLenBetween(String strValue, int minLength, int maxLength) {
        return strValue.length() >= minLength || strValue.length() <= maxLength;
    }

    protected boolean isValueGreaterThan(String strNumber, int maxValue) {
        return Double.parseDouble(strNumber) > maxValue;
    }

    protected boolean isValueLowerThan(String strNumber, int minValue) {
        return Double.parseDouble(strNumber) < minValue;
    }

    protected boolean isTypeString(Object anyValue) {
        return (anyValue instanceof String) || isTypeNumeric(anyValue);
    }

    protected boolean isTypeDouble(String strValue) {
        try {
            Double.parseDouble(strValue);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    protected boolean isTypeInteger(String strInteger) {
        try {
            Integer.parseInt(strInteger);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    protected boolean isTypeNumeric(String strNumber) {
        return isTypeInteger(strNumber) || isTypeDouble(strNumber);
    }

    protected boolean isTypeNumeric(Object anyValue) {
        return anyValue instanceof Number;
    }

    protected boolean isNullOrEmpty(String strValue) {
        return strValue == null || strValue.isEmpty() || strValue.isBlank();
    }

    protected boolean isTypeDate(String strValue) {
        return strValue.matches("\\d{4}-\\d{2}-\\d{2}");
    }

    protected boolean isTypeDatetime(String strValue) {
        return strValue.matches("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}");
    }

    protected boolean isTypeTime(String string) {
        return string.matches("\\d{2}:\\d{2}:\\d{2}");
    }

    protected boolean doesValueHaveAnyChar(String strValue, List<String> charsList) {
        for (String invalidCharacter : charsList) {
            if (strValue.contains(invalidCharacter)) {
                return true;
            }
        }
        return false;
    }

    protected boolean isTypeList(Object anyValue) {
        return anyValue instanceof List;
    }
}
