package com.eduardoaf.balance.mod_income.domain.exceptions;

import com.eduardoaf.balance.mod_shared.infrastructure.enums.HttpStatusCodeEnum;
import com.eduardoaf.balance.mod_shared.infrastructure.exceptions.AbstractInfrastructureException;

public final class CreateIncomeException extends AbstractInfrastructureException {

    public CreateIncomeException(String message, int statusCode) {
        super(message, statusCode);
    }

    public static void incomeAlreadyExists(String uuid) throws CreateIncomeException {

        throw new CreateIncomeException(
                String.format("There is another income with the same amount, date, payed for and payed from %s", uuid),
                HttpStatusCodeEnum.BAD_REQUEST.getValue()
        );
    }
}
