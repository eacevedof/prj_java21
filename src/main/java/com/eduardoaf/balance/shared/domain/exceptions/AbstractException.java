package com.eduardoaf.balance.shared.domain.exceptions;

import lombok.Getter;

@Getter
public abstract class AbstractException extends Exception implements InterfaceDomainException {

    private final int statusCode;

    protected AbstractException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }

}
