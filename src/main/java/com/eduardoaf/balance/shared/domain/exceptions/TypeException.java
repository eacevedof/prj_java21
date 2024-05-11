package com.eduardoaf.balance.shared.domain.exceptions;

import lombok.Getter;

@Getter
public final class TypeException extends Exception{

    private final int statusCode;

    public TypeException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    public static void valueIsNotNumeric(
        String field,
        Object currValue
    ) throws TypeException {
        var message = String.format(
                "The %s value `%s` is not numeric",
                field,
                getAsString(currValue)
        );
        throw new TypeException(
                message,
                400
        );
    }

    public static void valueIsNotInteger(
        String field,
        Object currValue
    ) throws TypeException {
        var message = String.format(
                "The %s value `%s` is not an integer",
                field,
                getAsString(currValue)
        );
        throw new TypeException(
                message,
                400
        );
    }

    public static void valueIsNotString(
        String field,
        Object currValue
    ) throws TypeException {
        var message = String.format(
                "The %s value `%s` is not a string",
                field,
                getAsString(currValue)
        );
        throw new TypeException(
                message,
                400
        );
    }

    public static void valueIsNotAList(
        String field,
        Object currValue
    ) throws TypeException {
        var message = String.format(
                "The %s value `%s` is not a list",
                field,
                getAsString(currValue)
        );
        throw new TypeException(
                message,
                400
        );
    }

    public static void valueIsEmpty(
        String field
    ) throws TypeException {
        var message = String.format(
                "The %s value is empty",
                field
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
