package com.eduardoaf.balance.mod_auth.application.dtos;

import com.eduardoaf.balance.mod_shared.infrastructure.formatters.StringFormatter;

public record AuthUserDto(
    String username,
    String password
) {
    public static AuthUserDto getInstance(
        String username,
        String password
    ) {
        StringFormatter stringFormatter = StringFormatter.getInstance();
        return new AuthUserDto(
            stringFormatter.getTrimOrNull(username),
            stringFormatter.getTrimOrNull(password)
        );
    }
}