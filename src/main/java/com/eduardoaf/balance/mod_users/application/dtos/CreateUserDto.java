package com.eduardoaf.balance.mod_users.application.dtos;

import com.eduardoaf.balance.mod_shared.infrastructure.formatters.StringFormatter;

public record CreateUserDto(
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
    String idProfile,
    String idAuthUser,
    String idPlatform
) {
    public static CreateUserDto getInstance(
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
        String idProfile,
        String idAuthUser,
        String idPlatform
    ) {
        StringFormatter stringFormatter = StringFormatter.getInstance();
        return new CreateUserDto(
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
            stringFormatter.getTrimOrNull(idProfile),
            stringFormatter.getTrimOrNull(idAuthUser),
            stringFormatter.getTrimOrNull(idPlatform)
        );
    }
}