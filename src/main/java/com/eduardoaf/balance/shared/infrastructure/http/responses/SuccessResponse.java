package com.eduardoaf.balance.shared.infrastructure.http.responses;

import org.springframework.beans.factory.annotation.Qualifier;

@Qualifier("SuccessResponse")
public record SuccessResponse(
    int code,
    String message,
    Object data
) {

    public static SuccessResponse getInstance(
        int code,
        String message,
        Object data
    ) {
        return new SuccessResponse(
            code,
            message,
            data
        );
    }

    public static SuccessResponse getInstance(
            int code,
            Object data
    ) {
        return new SuccessResponse(
            code,
            null,
            data
        );
    }
}
