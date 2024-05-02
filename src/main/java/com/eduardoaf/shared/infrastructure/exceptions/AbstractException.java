package com.eduardoaf.shared.infrastructure.exceptions;


import lombok.Getter;

@Getter
public abstract class AbstractException extends Exception{
    private final int statusCode;

    protected AbstractException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

}
