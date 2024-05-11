package com.eduardoaf.balance.shared.domain.exceptions;

import lombok.Getter;

@Getter
public final class ValueException extends Exception{

    private final int statusCode;

    public ValueException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    public static void isLowerThan(String field, int currValue, String minValue) throws ValueException {
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

    public static void isGreaterThan(String field, int currValue, int maxValue) throws ValueException {
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

    public static void minLength(String field, String currValue, int minLength) throws ValueException {
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

    public static void  maxLength(String field, String currValue, int maxLength) throws ValueException {
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
}
