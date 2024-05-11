package com.eduardoaf.balance.shared.domain.exceptions;

import lombok.Getter;

@Getter
public abstract class AbstractException extends Exception{

    private final int statusCode;

    protected AbstractException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    public static void errorOnCodeErpLength() throws AbstractException {
        throw new Exception(
                "Error on code erp length"
        );
    }
}
