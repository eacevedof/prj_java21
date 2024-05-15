package com.eduardoaf.balance.mod_auth.application.dtos;

import com.eduardoaf.balance.mod_shared.infrastructure.formatters.StringFormatter;
import com.eduardoaf.balance.mod_shared.infrastructure.formatters.NumberFormatter;

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

    public static AuthedUserDto getInstance(
        String id,
        String uuid,
        String codeErp,
        String description,
        String email,
        String fullname,
        String idParent,
        String idLanguage,
        String idProfile,
        String urlPicture,
        String jwt
    ) {
        var numberFormatter = NumberFormatter.getInstance();
        var stringFormatter = StringFormatter.getInstance();
        return new AuthedUserDto(
            numberFormatter.getIntegerOrNull(id),
            stringFormatter.getTrimOrNull(uuid),
            stringFormatter.getTrimOrNull(codeErp),
            stringFormatter.getTrimOrNull(description),
            stringFormatter.getTrimOrNull(email),
            stringFormatter.getTrimOrNull(fullname),
            numberFormatter.getIntegerOrNull(idParent),
            numberFormatter.getIntegerOrNull(idLanguage),
            numberFormatter.getIntegerOrNull(idProfile),
            stringFormatter.getTrimOrNull(urlPicture),
            stringFormatter.getAlwaysString(jwt)
        );
    }
}