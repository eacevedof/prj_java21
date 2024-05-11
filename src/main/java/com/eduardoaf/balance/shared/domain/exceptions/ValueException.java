package com.eduardoaf.balance.shared.domain.exceptions;

import lombok.Getter;

@Getter
public final class ValueException extends Exception{

    private final int statusCode;

    public ValueException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    public static void intValueIsLowerThan(String field, int currValue, String minValue) throws ValueException {
        var message = String.format(
                "The %s value `%s` is lower than the minimum value %s",
                field,
                currValue,
                minValue
        );
        throw new ValueException(
                message,
                400
        );
    }

    public static void intValueIsGreaterThan(String field, int currValue, int maxValue) throws ValueException {
        var message = String.format(
                "The %s value `%s` is greater than the maximum value %s",
                field,
                currValue,
                maxValue
        );
        throw new ValueException(
                message,
                400
        );
    }

    public static void wrongMinLength(String field, String currValue, int minLength) throws ValueException {
        var message = String.format(
                "The %s value `%s` is shorter than the minimum length %s",
                field,
                currValue,
                minLength
        );
        throw new ValueException(
                message,
                400
        );
    }

    public static void wrongMaxLength(String field, String currValue, int maxLength) throws ValueException {
        var message = String.format(
                "The %s value `%s` is larger than the maximum length %s",
                field,
                currValue,
                maxLength
        );
        throw new ValueException(
                message,
                400
        );
    }

    public static void wrongDateFormat(
        String field,
        Object currValue,
        String validFormat
    ) throws TypeException {
        var message = String.format(
                "The %s value `%s` does not match the format %s",
                field,
                getAsString(currValue),
                validFormat
        );
        throw new TypeException(
                message,
                400
        );
    }

    private static String getAsString(Object obj) {
        return obj == null ? "" : obj.toString();
    }
}
