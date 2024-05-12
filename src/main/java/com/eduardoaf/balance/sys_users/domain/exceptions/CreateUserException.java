package com.eduardoaf.balance.sys_users.domain.exceptions;

import com.eduardoaf.balance.shared.infrastructure.enums.HttpStatusCodeEnum;
import com.eduardoaf.balance.shared.infrastructure.exceptions.AbstractInfrastructureException;

public final class CreateUserException extends AbstractInfrastructureException {

    public CreateUserException(String message, int statusCode) {
        super(message, statusCode);
    }

    public static void userAlreadyExists(String email) throws CreateUserException {
        throw new CreateUserException(
            String.format("A user with the email %s already exists", email),
            HttpStatusCodeEnum.BAD_REQUEST.getValue()
        );
    }

}