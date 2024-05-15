package com.eduardoaf.balance.mod_auth.infrastructure.enums;

public enum AuthOkMessage {

    USER_SUCCESSFULLY_AUTHENTICATED(
        "User %s successfully authenticated"
    );

    private final String messageKey;

    AuthOkMessage(String messageKey) {
        this.messageKey = messageKey;
    }

    public String getValue() {
        return messageKey;
    }

}
