package com.eduardoaf.balance.mod_shared.domain.exceptions;

import com.eduardoaf.balance.mod_auth.domain.enums.AuthNokMessage;
import com.eduardoaf.balance.mod_shared.infrastructure.enums.HttpStatusCodeEnum;

public final class AuthUserException extends AbstractDomainException {

    public AuthUserException(String message, int statusCode) {
        super(message, statusCode);
    }

    public static void unauthorizedUser(
        String jwtToken
    ) throws AuthUserException {
        throw new AuthUserException(
            String.format(
                AuthNokMessage.WRONG_ACCOUNT_OR_PASSWORD.getValue(),
                jwtToken
            ),
            HttpStatusCodeEnum.UNAUTHORIZED.getValue()
        );
    }

}