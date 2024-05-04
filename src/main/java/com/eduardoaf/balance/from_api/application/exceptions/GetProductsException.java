package com.eduardoaf.balance.from_api.application.exceptions;

import com.eduardoaf.balance.shared.infrastructure.exceptions.AbstractException;
import com.eduardoaf.balance.shared.infrastructure.enums.HttpStatusCodeEnum;

public final class GetProductsException extends AbstractException {

    public GetProductsException(String message, int statusCode) {
        super(message, statusCode);
    }

    public static void ErrorOnReadingEndpoint(String endpoint) throws Exception {
        throw new GetProductsException(
                "Error reading endpoint:".concat(endpoint),
                HttpStatusCodeEnum.SERVICE_UNAVAILABLE.getValue()
        );
    }
}
