package com.eduardoaf.balance.shared.domain.exceptions;

import lombok.Getter;

@Getter
public final class TypeException extends Exception{

    private final int statusCode;

    public TypeException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    public static void isLowerThan(String field, int currValue, String minValue) throws TypeException {
        var message = String.format(
                "The %s value `%s` is lower than the minimum value %s",
                field,
                currValue,
                minValue
        );
        throw new TypeException(
                message,
                400
        );
    }

}
