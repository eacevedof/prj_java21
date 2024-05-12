package com.eduardoaf.balance.sys_users.application.dtos;

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
        return new CreatedUserDto(
            id,
            uuid.trim(),
            codeErp.trim(),
            description.trim(),
            email.trim(),
            secret.trim(),
            phone.trim(),
            fullname.trim(),
            address.trim(),
            birthdate,
            idParent,
            idGender,
            idNationality,
            idCountry,
            idLanguage,
            idProfile,
            urlPicture.trim(),
            dateValidated,
            logAttempts
        );
    }
}