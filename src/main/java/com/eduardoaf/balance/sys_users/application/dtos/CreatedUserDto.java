package com.eduardoaf.balance.sys_users.application.dtos;

import com.eduardoaf.balance.shared.infrastructure.formatters.StringFormatter;

public record CreatedUserDto(
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

    public static CreatedUserDto getInstance(
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
        return new CreatedUserDto(
            id,

            stringFormatter.getTrimmed(uuid),
            stringFormatter.getTrimmed(codeErp),
            stringFormatter.getTrimmed(description),
            stringFormatter.getTrimmed(email),
            stringFormatter.getTrimmed(secret),
            stringFormatter.getTrimmed(phone),
            stringFormatter.getTrimmed(fullname),
            stringFormatter.getTrimmed(address),
            stringFormatter.getTrimmed(birthdate),

            idParent,
            idGender,
            idNationality,
            idCountry,
            idLanguage,
            idProfile,

            stringFormatter.getTrimmed(urlPicture),
            stringFormatter.getTrimmed(dateValidated),

            logAttempts
        );
    }
}