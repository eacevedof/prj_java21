package com.eduardoaf.balance.mod_shared.infrastructure.enums;

public enum NokMessageEnum {
    UNEXPECTED_ERROR_OCCURRED(
    "Sorry, an unexpected error occurred. Please try again later. If the problem persists, please contact the support team"
    );
    private final String envKey;

    NokMessageEnum(String envKey) {
        this.envKey = envKey;
    }

    public String getValue() {
        return envKey;
    }
}
