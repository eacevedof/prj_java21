package com.eduardoaf.balance.mod_users.domain.entities;

import com.eduardoaf.balance.mod_shared.domain.entities.AbstractEntity;
import com.eduardoaf.balance.mod_shared.infrastructure.formatters.NumberFormatter;
import com.eduardoaf.balance.mod_shared.infrastructure.formatters.StringFormatter;

import java.util.Map;

import lombok.Getter;

@Getter
public class BaseUserEntity extends AbstractEntity {

    private final String uuid;
    private final String codeErp;
    private final String description;
    private final String email;
    private final String secret;
    private final String phone;
    private final String fullname;
    private final String address;
    private final String birthdate;
    private final Integer idParent;
    private final Integer idGender;
    private final Integer idNationality;
    private final Integer idCountry;
    private final Integer idLanguage;
    private final Integer idProfile;
    private final String urlPicture;
    private final String dateValidated;
    private final Integer logAttempts;


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
        Integer logAttempts,
        String insertUser,
        String insertPlatform
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
        this.insertUser = insertUser;
        this.insertPlatform = insertPlatform;
    }

    public static BaseUserEntity fromMapRow(Map<String, String> mapRow) {
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
            numberFormatter.getIntegerOrNull(mapRow.get("log_attempts")),
            stringFormatter.getTrimOrNull(mapRow.get("insert_user")),
            stringFormatter.getTrimOrNull(mapRow.get("insert_platform"))
        );
        loadParentByMapRow(baseUserEntity, mapRow);
        return baseUserEntity;
    }

    public static BaseUserEntity fromStrings(
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
        String logAttempts,
        String insertUser,
        String insertPlatform
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
            numberFormatter.getIntegerOrNull(logAttempts),
            stringFormatter.getTrimOrNull(insertUser),
            stringFormatter.getTrimOrNull(insertPlatform)
        );
    }
}