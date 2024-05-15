package com.eduardoaf.balance.mod_auth.application.dtos;

import  com.eduardoaf.balance.mod_auth.domain.entities.AuthUserEntity;

public record AuthedUserDto(
    Integer id,
    String uuid,
    String codeErp,
    String description,
    String email,
    String fullname,
    Integer idParent,
    Integer idLanguage,
    Integer idProfile,
    String urlPicture,
    String jwt
) {

    public static AuthedUserDto fromAuthUserEntityAndJwt(
        AuthUserEntity authUserEntity,
        String jwt
    ) {
        return new AuthedUserDto(
            authUserEntity.getId(),
            authUserEntity.getUuid(),
            authUserEntity.getCodeErp(),
            authUserEntity.getDescription(),
            authUserEntity.getEmail(),
            authUserEntity.getFullname(),
            authUserEntity.getIdParent(),
            authUserEntity.getIdLanguage(),
            authUserEntity.getIdProfile(),
            authUserEntity.getUrlPicture(),
            jwt
        );
    }
}