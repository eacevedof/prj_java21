package com.eduardoaf.balance.mod_auth.domain.exceptions;

import com.eduardoaf.balance.mod_shared.infrastructure.enums.HttpStatusCodeEnum;
import com.eduardoaf.balance.mod_shared.infrastructure.exceptions.AbstractInfrastructureException;

public final class AuthUserException extends AbstractInfrastructureException {

    public AuthUserException(String message, int statusCode) {
        super(message, statusCode);
    }

    public static void userAlreadyExists(String email) throws AuthUserException {
        throw new AuthUserException(
            String.format("A user with the email %s already exists", email),
            HttpStatusCodeEnum.BAD_REQUEST.getValue()
        );
    }

}