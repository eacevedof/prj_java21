package com.eduardoaf.balance.shared.infrastructure.http.responses;

import org.springframework.beans.factory.annotation.Qualifier;

@Qualifier("ErrorResponse")
public record ErrorResponse(
    int code,
    Object details,
    String message
) {

    public static ErrorResponse getInstance(
        int code,
        Object details,
        String message
    ) {
        return new ErrorResponse(
            code,
            details,
            message
        );
    }

    public static ErrorResponse getInstance(
            int code,
            Object details
    ) {
        return new ErrorResponse(
                code,
                details,
                null
        );
    }
}
