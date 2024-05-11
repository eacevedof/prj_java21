package com.eduardoaf.balance.shared.domain.exceptions;

import lombok.Getter;

@Getter
public final class LengthException extends Exception{

    private final int statusCode;

    public LengthException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    public static LengthException getInstance(String message, int statusCode) {
        return new LengthException(
                message,
                statusCode
        );
    }

    public static void isLowerThan(String field, String currValue, String minValue) throws LengthException {
        var message = String.format(
                "The %s value `%s` is lower than the minimum value %s",
                field,
                currValue,
                minValue
        );
        throw new LengthException(
                message,
                400
        );
    }
}
