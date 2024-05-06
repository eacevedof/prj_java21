package com.eduardoaf.balance.shared.infrastructure.http.responses;

import org.springframework.beans.factory.annotation.Qualifier;

@Qualifier("SuccessResponse")
public record SuccessResponse(
    int code,
    Object details,
    String message
) {
    public static SuccessResponse getInstance(
        int code,
        Object details,
        String message
    ) {
        return new SuccessResponse(
            code,
            details,
            message
        );
    }

    public static SuccessResponse getInstance(
            int code,
            Object details
    ) {
        return new SuccessResponse(
                code,
                details,
                null
        );
    }
}
