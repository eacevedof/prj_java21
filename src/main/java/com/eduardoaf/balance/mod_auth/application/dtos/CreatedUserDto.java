package com.eduardoaf.balance.mod_auth.application.dtos;

import com.eduardoaf.balance.mod_shared.infrastructure.formatters.StringFormatter;

public record AuthdUserDto(
    Integer id,
    String uuid,
    String codeErp,
    String description,
    String email,
    String secret,
    String phone,
    String fullname,
    String address,
    String birthdate,
    Integer idParent,
    Integer idGender,
    Integer idNationality,
    Integer idCountry,
    Integer idLanguage,
    Integer idProfile,
    String urlPicture,
    String dateValidated,
    Integer logAttempts
) {

    public static AuthdUserDto getInstance(
        Integer id,
        String uuid,
        String codeErp,
        String description,
        String email,
        String secret,
        String phone,
        String fullname,
        String address,
        String birthdate,
        Integer idParent,
        Integer idGender,
        Integer idNationality,
        Integer idCountry,
        Integer idLanguage,
        Integer idProfile,
        String urlPicture,
        String dateValidated,
        Integer logAttempts
    ) {
        var stringFormatter = StringFormatter.getInstance();
        return new AuthdUserDto(
            id,

            stringFormatter.getTrimOrNull(uuid),
            stringFormatter.getTrimOrNull(codeErp),
            stringFormatter.getTrimOrNull(description),
            stringFormatter.getTrimOrNull(email),
            stringFormatter.getTrimOrNull(secret),
            stringFormatter.getTrimOrNull(phone),
            stringFormatter.getTrimOrNull(fullname),
            stringFormatter.getTrimOrNull(address),
            stringFormatter.getTrimOrNull(birthdate),

            idParent,
            idGender,
            idNationality,
            idCountry,
            idLanguage,
            idProfile,

            stringFormatter.getTrimOrNull(urlPicture),
            stringFormatter.getTrimOrNull(dateValidated),

            logAttempts
        );
    }
}