package com.eduardoaf.balance.mod_shared.domain.exceptions;

import lombok.Getter;

@Getter
public abstract class AbstractDomainException extends Exception implements InterfaceDomainException {

    private final int statusCode;

    protected AbstractDomainException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

}
