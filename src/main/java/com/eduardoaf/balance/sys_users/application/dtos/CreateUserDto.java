package com.eduardoaf.balance.sys_users.application.dtos;

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
    String idProfile
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
        String idProfile
    ) {
        return new CreateUserDto(
            codeErp == null ? "" : codeErp.trim(),
            email == null ? "" : email.trim(),
            secret == null ? "" : secret.trim(),
            phone == null ? "" : phone.trim(),
            fullname == null ? "" : fullname.trim(),
            address == null ? "" : address.trim(),
            birthdate == null ? "" : birthdate.trim(),
            idParent == null ? "" : idParent.trim(),
            idGender == null ? "" : idGender.trim(),
            idNationality == null ? "" : idNationality.trim(),
            idCountry == null ? "" : idCountry.trim(),
            idLanguage == null ? "" : idLanguage.trim(),
            idProfile == null ? "" : idProfile.trim()
        );
    }
}