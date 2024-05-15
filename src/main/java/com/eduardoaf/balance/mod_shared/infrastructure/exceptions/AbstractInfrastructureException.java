package com.eduardoaf.balance.mod_shared.infrastructure.exceptions;

import lombok.Getter;

@Getter
public abstract class AbstractInfrastructureException extends Exception{

    private final int statusCode;

    protected AbstractInfrastructureException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

}
