package com.eduardoaf.balance.mod_users.domain.entities;

import com.eduardoaf.balance.mod_shared.domain.entities.AbstractEntity;
import com.eduardoaf.balance.mod_shared.infrastructure.formatters.NumberFormatter;
import com.eduardoaf.balance.mod_shared.infrastructure.formatters.StringFormatter;

import java.util.Map;

import lombok.Getter;

@Getter
public final class BaseUserEntity extends AbstractEntity {

    public final String uuid;
    public final String codeErp;
    public final String description;
    public final String email;
    public final String secret;
    public final String phone;
    public final String fullname;
    public final String address;
    public final String birthdate;
    public final Integer idParent;
    public final Integer idGender;
    public final Integer idNationality;
    public final Integer idCountry;
    public final Integer idLanguage;
    public final Integer idProfile;
    public final String urlPicture;
    public final String dateValidated;
    public final Integer logAttempts;

    public BaseUserEntity(
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

    public static BaseUserEntity getInstanceByMapRow(Map<String, String> mapRow) {
        StringFormatter stringFormatter = new StringFormatter();
        NumberFormatter numberFormatter = new NumberFormatter();

        BaseUserEntity baseUserEntity = new BaseUserEntity(
            numberFormatter.getIntegerOrNull(mapRow.get("id")),
            stringFormatter.getTrimOrNull(mapRow.get("uuid")),
            stringFormatter.getTrimOrNull(mapRow.get("code_erp")),
            stringFormatter.getTrimOrNull(mapRow.get("description")),
            stringFormatter.getTrimOrNull(mapRow.get("email")),
            stringFormatter.getTrimOrNull(mapRow.get("secret")),
            stringFormatter.getTrimOrNull(mapRow.get("phone")),
            stringFormatter.getTrimOrNull(mapRow.get("fullname")),
            stringFormatter.getTrimOrNull(mapRow.get("address")),
            stringFormatter.getTrimOrNull(mapRow.get("birthdate")),
            numberFormatter.getIntegerOrNull(mapRow.get("id_parent")),
            numberFormatter.getIntegerOrNull(mapRow.get("id_gender")),
            numberFormatter.getIntegerOrNull(mapRow.get("id_nationality")),
            numberFormatter.getIntegerOrNull(mapRow.get("id_country")),
            numberFormatter.getIntegerOrNull(mapRow.get("id_language")),
            numberFormatter.getIntegerOrNull(mapRow.get("id_profile")),
            stringFormatter.getTrimOrNull(mapRow.get("url_picture")),
            stringFormatter.getTrimOrNull(mapRow.get("date_validated")),
            numberFormatter.getIntegerOrNull(mapRow.get("log_attempts"))
        );
        loadParentByMapRow(baseUserEntity, mapRow);
        return baseUserEntity;
    }

    public static BaseUserEntity getInstanceFromStrings(
        String id,
        String uuid,
        String codeErp,
        String description,
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

        String urlPicture,
        String dateValidated,
        String logAttempts
    ) {
        StringFormatter stringFormatter = new StringFormatter();
        NumberFormatter numberFormatter = new NumberFormatter();
        
        return new BaseUserEntity(
            numberFormatter.getIntegerOrNull(id),
            stringFormatter.getTrimOrNull(uuid),
            stringFormatter.getTrimOrNull(codeErp),
            stringFormatter.getTrimOrNull(description),
            stringFormatter.getTrimOrNull(email),
            stringFormatter.getTrimOrNull(secret),
            stringFormatter.getTrimOrNull(phone),
            stringFormatter.getTrimOrNull(fullname),
            stringFormatter.getTrimOrNull(address),
            stringFormatter.getTrimOrNull(birthdate),
            numberFormatter.getIntegerOrNull(idParent),
            numberFormatter.getIntegerOrNull(idGender),
            numberFormatter.getIntegerOrNull(idNationality),
            numberFormatter.getIntegerOrNull(idCountry),
            numberFormatter.getIntegerOrNull(idLanguage),
            numberFormatter.getIntegerOrNull(idProfile),
            stringFormatter.getTrimOrNull(urlPicture),
            stringFormatter.getTrimOrNull(dateValidated),
            numberFormatter.getIntegerOrNull(logAttempts)
        );
    }

    public static BaseUserEntity getInstance(
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
        return new BaseUserEntity(
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