package com.eduardoaf.balance.mod_shared.domain.exceptions;

import lombok.Getter;

@Getter
public final class DomainValueException extends Exception implements InterfaceDomainException{

    private final int statusCode;

    public DomainValueException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    public static void intValueIsLowerThan(String field, int currValue, String minValue) throws DomainValueException {
        var message = String.format(
                "The `%s` value: `%s` is lower than the minimum value %s",
                field,
                currValue,
                minValue
        );
        throw new DomainValueException(
                message,
                400
        );
    }

    public static void intValueIsGreaterThan(String field, int currValue, int maxValue) throws DomainValueException {
        var message = String.format(
                "The `%s` value: `%s` is greater than the maximum value %s",
                field,
                currValue,
                maxValue
        );
        throw new DomainValueException(
                message,
                400
        );
    }
    
    public static void wrongMinLength(String field, String currValue, int minLength) throws DomainValueException {
        var currLength = currValue.length();
        var message = String.format(
                "The `%s` value: `%s` (%s) is shorter than the minimum length: %s",
                field,
                currValue,
                currLength,
                minLength
        );
        throw new DomainValueException(
                message,
                400
        );
    }

    public static void wrongMaxLength(String field, String currValue, int maxLength) throws DomainValueException {
        var currLength = currValue.length();
        var message = String.format(
                "The `%s` value: `%s` (%s) is larger than the maximum length: %s",
                field,
                currValue,
                currLength,
                maxLength
        );
        throw new DomainValueException(
                message,
                400
        );
    }

    public static void wrongDateFormat(
        String field,
        Object currValue,
        String validFormat
    ) throws DomainValueException {
        var message = String.format(
                "The `%s` value: `%s` does not match the format %s",
                field,
                getAsString(currValue),
                validFormat
        );
        throw new DomainValueException(
                message,
                400
        );
    }

    public static void valueIsEmpty(
            String field
    ) throws DomainValueException {
        var message = String.format(
                "The `%s` value is empty",
                field
        );
        throw new DomainValueException(
                message,
                400
        );
    }

    public static void wrongEmailFormat(
        String field,
        Object currValue,
        String validFormat
    ) throws DomainValueException {
        var message = String.format(
            "The `%s` value: `%s` does not match the format %s",
            field,
            getAsString(currValue),
            validFormat
        );
        throw new DomainValueException(
            message,
            400
        );
    }

    public static void valueIsLowerThanMinimum(String field, String currValue, int minValue) throws DomainValueException {
        var message = String.format(
                "The `%s` value: `%s` is lower than the minimum value %s",
                field,
                currValue,
                minValue
        );
        throw new DomainValueException(
                message,
                400
        );
    }

    private static String getAsString(Object obj) {
        return obj == null ? "" : obj.toString();
    }

}
