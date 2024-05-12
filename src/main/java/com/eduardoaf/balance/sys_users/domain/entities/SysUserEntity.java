package com.eduardoaf.balance.sys_users.domain.entities;

import com.eduardoaf.balance.shared.domain.entities.AbstractEntity;

import java.time.LocalDateTime;

public final class SysUserEntity extends AbstractEntity {

    public final Integer id;
    public final String uuid;
    public final String codeErp;
    public final String description;
    public final String email;
    public final String secret;
    public final String phone;
    public final String fullname;
    public final String address;
    public final LocalDateTime birthdate;
    public final Integer idParent;
    public final Integer idGender;
    public final Integer idNationality;
    public final Integer idCountry;
    public final int idLanguage;
    public final int idProfile;
    public final String urlPicture;
    public final LocalDateTime dateValidated;
    public final int logAttempts;

    public SysUserEntity(
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
        this.id = id;
        this.uuid = uuid;
        this.codeErp = codeErp;
        this.description = description;
        this.email = email;
        this.secret = secret;
        this.phone = phone;
        this.fullname = fullname;
        this.address = address;
        this.birthdate = birthdate;
        this.idParent = idParent;
        this.idGender = idGender;
        this.idNationality = idNationality;
        this.idCountry = idCountry;
        this.idLanguage = idLanguage;
        this.idProfile = idProfile;
        this.urlPicture = urlPicture;
        this.dateValidated = dateValidated;
        this.logAttempts = logAttempts;
    }

    public static SysUserEntity getInstance(
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
        return new SysUserEntity(
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