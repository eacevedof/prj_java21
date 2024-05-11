package com.eduardoaf.balance.app_cap_income.application.exceptions;

import com.eduardoaf.balance.shared.infrastructure.enums.HttpStatusCodeEnum;
import com.eduardoaf.balance.shared.infrastructure.exceptions.AbstractException;

public final class CreateIncomeException extends AbstractException {

    public CreateIncomeException(String message, int statusCode) {
        super(message, statusCode);
    }

    public static void errorOnCodeErpLength() throws CreateIncomeException {
        throw new CreateIncomeException(
                "Error on code erp length",
                HttpStatusCodeEnum.BAD_REQUEST.getValue()
        );
    }
}
