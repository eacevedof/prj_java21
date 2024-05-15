package com.eduardoaf.balance.mod_users.application.dtos;

import com.eduardoaf.balance.mod_shared.infrastructure.formatters.StringFormatter;
import com.eduardoaf.balance.mod_users.domain.entities.BaseUserEntity;

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

    public static CreatedUserDto fromBaseUserEntity(
        BaseUserEntity baseUserEntity
    ) {
        return new CreatedUserDto(
            baseUserEntity.getId(),
            baseUserEntity.getUuid(),
            baseUserEntity.getCodeErp(),
            baseUserEntity.getDescription(),
            baseUserEntity.getEmail(),
            baseUserEntity.getSecret(),
            baseUserEntity.getPhone(),
            baseUserEntity.getFullname(),
            baseUserEntity.getAddress(),
            baseUserEntity.getBirthdate(),
            baseUserEntity.getIdParent(),
            baseUserEntity.getIdGender(),
            baseUserEntity.getIdNationality(),
            baseUserEntity.getIdCountry(),
            baseUserEntity.getIdLanguage(),
            baseUserEntity.getIdProfile(),
            baseUserEntity.getUrlPicture(),
            baseUserEntity.getDateValidated(),
            baseUserEntity.getLogAttempts()
        );
    }


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