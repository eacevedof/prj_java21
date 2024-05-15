package com.eduardoaf.balance.mod_auth.application.dtos;

import com.eduardoaf.balance.mod_shared.infrastructure.formatters.StringFormatter;

public record AuthUserDto(
    String codeErp,
    String email,
    String secret,
    String phone,
    String fullname,
    String address,
    String birthdate,
    String idParent,
    String idGender,
    String idNationality,
    String idCountry,
    String idLanguage,
    String idProfile
) {
    public static AuthUserDto getInstance(
        String codeErp,
        String email,
        String secret,
        String phone,
        String fullname,
        String address,
        String birthdate,
        String idParent,
        String idGender,
        String idNationality,
        String idCountry,
        String idLanguage,
        String idProfile
    ) {
        StringFormatter stringFormatter = StringFormatter.getInstance();
        return new AuthUserDto(
            stringFormatter.getTrimOrNull(codeErp),
            stringFormatter.getTrimOrNull(email),
            stringFormatter.getTrimOrNull(secret),
            stringFormatter.getTrimOrNull(phone),
            stringFormatter.getTrimOrNull(fullname),
            stringFormatter.getTrimOrNull(address),
            stringFormatter.getTrimOrNull(birthdate),
            stringFormatter.getTrimOrNull(idParent),
            stringFormatter.getTrimOrNull(idGender),
            stringFormatter.getTrimOrNull(idNationality),
            stringFormatter.getTrimOrNull(idCountry),
            stringFormatter.getTrimOrNull(idLanguage),
            stringFormatter.getTrimOrNull(idProfile)
        );
    }
}