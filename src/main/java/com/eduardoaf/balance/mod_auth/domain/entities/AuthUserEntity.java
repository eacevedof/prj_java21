package com.eduardoaf.balance.mod_auth.domain.entities;

import com.eduardoaf.balance.mod_income.domain.entities.AppCapIncomeEntity;
import lombok.Getter;

import com.eduardoaf.balance.mod_shared.domain.entities.AbstractEntity;
import com.eduardoaf.balance.mod_shared.infrastructure.formatters.NumberFormatter;
import com.eduardoaf.balance.mod_shared.infrastructure.formatters.StringFormatter;

import java.util.Map;


@Getter
public final class AuthUserEntity extends AbstractEntity {
    
    private final String uuid;
    private final String codeErp;
    private final String email;
    private final String secret;
    private final String fullname;
    private final Integer idParent;
    private final Integer idLanguage;
    private final Integer idProfile;
    private final String urlPicture;

    private AuthUserEntity(
        Integer id,
        String uuid,
        String codeErp,
        String email,
        String secret,
        String fullname,
        Integer idParent,
        Integer idLanguage,
        Integer idProfile,
        String urlPicture
    ) {
        this.id = id;
        this.uuid = uuid;
        this.codeErp = codeErp;
        this.email = email;
        this.secret = secret;
        this.fullname = fullname;
        this.idParent = idParent;
        this.idLanguage = idLanguage;
        this.idProfile = idProfile;
        this.urlPicture = urlPicture;
    }

    private static AuthUserEntity fromStrings(
        String id,
        String uuid,
        String codeErp,
        String email,
        String secret,
        String fullname,
        String idParent,
        String idLanguage,
        String idProfile,

        String urlPicture
    ) {
        NumberFormatter numberFormatter = NumberFormatter.getInstance();
        StringFormatter stringFormatter = StringFormatter.getInstance();
        return new AuthUserEntity(
            numberFormatter.getIntegerOrNull(id),
            stringFormatter.getTrimOrNull(uuid),
            stringFormatter.getTrimOrNull(codeErp),
            stringFormatter.getTrimOrNull(email),
            stringFormatter.getTrimOrNull(secret),
            stringFormatter.getTrimOrNull(fullname),
            numberFormatter.getIntegerOrNull(idParent),
            numberFormatter.getIntegerOrNull(idLanguage),
            numberFormatter.getIntegerOrNull(idProfile),
            stringFormatter.getTrimOrNull(urlPicture)
        );
    }

    public static AuthUserEntity fromMapRow(Map<String, String> mapRow) {
        StringFormatter stringFormatter = StringFormatter.getInstance();
        NumberFormatter numberFormatter = NumberFormatter.getInstance();

        AuthUserEntity authUserEntity = new AuthUserEntity(
            numberFormatter.getIntegerOrNull(mapRow.get("id")),
            stringFormatter.getTrimOrNull(mapRow.get("uuid")),
            stringFormatter.getTrimOrNull(mapRow.get("code_erp")),
            stringFormatter.getTrimOrNull(mapRow.get("email")),
            stringFormatter.getTrimOrNull(mapRow.get("secret")),
            stringFormatter.getTrimOrNull(mapRow.get("fullname")),
            numberFormatter.getIntegerOrNull(mapRow.get("id_parent")),
            numberFormatter.getIntegerOrNull(mapRow.get("id_language")),
            numberFormatter.getIntegerOrNull(mapRow.get("id_profile")),
            stringFormatter.getTrimOrNull(mapRow.get("url_picture"))
        );
        loadParentByMapRow(authUserEntity, mapRow);
        return authUserEntity;
    }


}