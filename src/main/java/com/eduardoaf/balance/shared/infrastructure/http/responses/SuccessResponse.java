package com.eduardoaf.balance.shared.infrastructure.http.responses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


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
