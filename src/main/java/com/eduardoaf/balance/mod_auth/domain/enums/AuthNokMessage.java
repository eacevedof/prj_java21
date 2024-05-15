package com.eduardoaf.balance.mod_auth.domain.enums;

public enum AuthNokMessage {
    WRONG_ACCOUNT_OR_PASSWORD(
    "Wrong user or email: %s"
    );

    private final String messageKey;

    AuthNokMessage(String messageKey) {
    this.messageKey = messageKey;
    }

    public String getValue() {
    return messageKey;
    }

}
