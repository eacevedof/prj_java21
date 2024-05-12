package com.eduardoaf.balance.sys_users.application.dtos;

import java.time.LocalDateTime;

public record CreateUserDto(
        Integer id,
        String uuid,
        String codeErp,
        String description,
        String email,
        String secret,
        String phone,
        String fullname,
        String address,
        LocalDateTime birthdate,
        Integer idParent,
        Integer idGender,
        Integer idNationality,
        Integer idCountry,
        int idLanguage,
        int idProfile,
        String urlPicture,
        LocalDateTime dateValidated,
        int logAttempts
) {
    public static CreateUserDto getInstance(
            Integer id,
            String uuid,
            String codeErp,
            String description,
            String email,
            String secret,
            String phone,
            String fullname,
            String address,
            LocalDateTime birthdate,
            Integer idParent,
            Integer idGender,
            Integer idNationality,
            Integer idCountry,
            int idLanguage,
            int idProfile,
            String urlPicture,
            LocalDateTime dateValidated,
            int logAttempts
    ) {
        return new CreateUserDto(
                id,
                uuid == null ? "" : uuid.trim(),
                codeErp == null ? "" : codeErp.trim(),
                description == null ? "" : description.trim(),
                email == null ? "" : email.trim(),
                secret == null ? "" : secret.trim(),
                phone == null ? "" : phone.trim(),
                fullname == null ? "" : fullname.trim(),
                address == null ? "" : address.trim(),
                birthdate,
                idParent,
                idGender,
                idNationality,
                idCountry,
                idLanguage,
                idProfile,
                urlPicture == null ? "" : urlPicture.trim(),
                dateValidated,
                logAttempts
        );
    }
}