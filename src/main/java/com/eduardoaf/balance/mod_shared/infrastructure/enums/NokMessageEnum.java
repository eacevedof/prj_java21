package com.eduardoaf.balance.mod_shared.infrastructure.enums;

public enum NokMessageEnum {
    UNEXPECTED_ERROR_OCCURRED(
    "Sorry, an unexpected error occurred. Please try again later. If the problem persists, please contact the support team"
    );
    private final String messageKey;

    NokMessageEnum(String messageKey) {
        this.messageKey = messageKey;
    }

    public String getValue() {
        return messageKey;
    }
}
