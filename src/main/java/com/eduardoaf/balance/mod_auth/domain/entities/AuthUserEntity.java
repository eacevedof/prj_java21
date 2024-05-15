package com.eduardoaf.balance.mod_auth.domain.entities;

import com.eduardoaf.balance.mod_shared.domain.entities.AbstractEntity;
import com.eduardoaf.balance.mod_shared.infrastructure.formatters.NumberFormatter;
import com.eduardoaf.balance.mod_shared.infrastructure.formatters.StringFormatter;

public final class AuthUserEntity extends AbstractEntity {

    public final Integer id;
    public final String uuid;
    public final String codeErp;
    public final String email;
    public final String secret;
    public final String fullname;
    public final Integer idParent;
    public final Integer idLanguage;
    public final Integer idProfile;
    public final String urlPicture;


    public AuthUserEntity(
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

    public static AuthUserEntity getInstanceById(
            Integer id
    ) {
        return new AuthUserEntity(
                id,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null
        );
    }

    public static AuthUserEntity getInstanceByEmail(
        String email
    ) {
        StringFormatter stringFormatter = StringFormatter.getInstance();
        return new AuthUserEntity(
        null,
        null,
        stringFormatter.getTrimOrNull(email),
        null,
        null,
        null,
        null,
        null,
        null,
        null
        );
    }

    public static AuthUserEntity getInstance(
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
        StringFormatter stringFormatter = StringFormatter.getInstance();
        return new AuthUserEntity(
            id,
            stringFormatter.getTrimOrNull(uuid),
            stringFormatter.getTrimOrNull(codeErp),
            stringFormatter.getTrimOrNull(email),
            stringFormatter.getTrimOrNull(secret),
            stringFormatter.getTrimOrNull(fullname),
            idParent,
            idLanguage,
            idProfile,
            stringFormatter.getTrimOrNull(urlPicture)
        );
    }

    public static AuthUserEntity getInstanceFromStrings(
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
        StringFormatter stringFormatter = StringFormatter.getInstance();
        NumberFormatter numberFormatter = NumberFormatter.getInstance();
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

}