package com.eduardoaf.balance.app_cap_income.application.exceptions;

import com.eduardoaf.balance.shared.infrastructure.enums.HttpStatusCodeEnum;
import com.eduardoaf.balance.shared.infrastructure.exceptions.AbstractException;

public final class CreateIncomeException extends AbstractException {

    public CreateIncomeException(String message, int statusCode) {
        super(message, statusCode);
    }

    public static void ErrorOnReadingEndpoint(String endpoint) throws CreateIncomeException {
        throw new CreateIncomeException(
                "Error reading endpoint:".concat(endpoint),
                HttpStatusCodeEnum.SERVICE_UNAVAILABLE.getValue()
        );
    }

}
