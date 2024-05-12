package com.eduardoaf.balance.sys_users.application.dtos;

import java.time.LocalDateTime;

public record CreatedUserDto(
        int id,
        String uuid,
        String codeErp,
        String description,
        String email,
        String secret,
        String phone,
        String fullname,
        String address,
        LocalDateTime birthdate,
        int idParent,
        int idGender,
        int idNationality,
        int idCountry,
        int idLanguage,
        int idProfile,
        String urlPicture,
        LocalDateTime dateValidated,
        int logAttempts
) {

    public static CreatedUserDto getInstance(
            int id,
            String uuid,
            String codeErp,
            String description,
            String email,
            String secret,
            String phone,
            String fullname,
            String address,
            LocalDateTime birthdate,
            int idParent,
            int idGender,
            int idNationality,
            int idCountry,
            int idLanguage,
            int idProfile,
            String urlPicture,
            LocalDateTime dateValidated,
            int logAttempts
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