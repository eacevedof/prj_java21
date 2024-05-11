package com.eduardoaf.balance.shared.domain.validators;

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
}
