package com.eduardoaf.balance.mod_auth.domain.exceptions;

import com.eduardoaf.balance.mod_shared.infrastructure.enums.HttpStatusCodeEnum;
import com.eduardoaf.balance.mod_shared.infrastructure.exceptions.AbstractInfrastructureException;
import com.eduardoaf.balance.mod_auth.domain.enums.AuthNokMessage;

public final class AuthUserException extends AbstractInfrastructureException {

    public AuthUserException(String message, int statusCode) {
        super(message, statusCode);
    }

    public static void wrongAccountOrPassword(String errorCode) throws AuthUserException {
        throw new AuthUserException(
            String.format(
                AuthNokMessage.WRONG_ACCOUNT_OR_PASSWORD.getValue(),
                errorCode
            ),
            HttpStatusCodeEnum.BAD_REQUEST.getValue()
        );
    }

}