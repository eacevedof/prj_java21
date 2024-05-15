package com.eduardoaf.balance.mod_auth.application.services;

import com.eduardoaf.balance.mod_auth.application.dtos.AuthUserDto;
import com.eduardoaf.balance.mod_auth.domain.entities.SysUserEntity;
import com.eduardoaf.balance.mod_auth.infrastructure.repositories.AuthUserReaderRepository;
import com.eduardoaf.balance.mod_auth.infrastructure.repositories.AuthUserWriterRepository;
import com.eduardoaf.balance.mod_auth.application.dtos.AuthedUserDto;
import com.eduardoaf.balance.mod_auth.domain.validators.AuthUserValidator;
import com.eduardoaf.balance.mod_shared.infrastructure.file.Log;
import com.eduardoaf.balance.mod_shared.infrastructure.formatters.NumberFormatter;
import com.eduardoaf.balance.mod_shared.infrastructure.formatters.PasswordFormatter;
import com.eduardoaf.balance.mod_shared.infrastructure.formatters.StringFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public final class AuthUserService {

    private final Log log;
    private final AuthUserValidator authUserValidator;
    private final AuthUserWriterRepository sysUserWriterRepository;
    private final AuthUserReaderRepository sysUserReaderRepository;
    private final PasswordFormatter passwordFormatter;
    private final NumberFormatter numberFormatter;
    private final StringFormatter stringFormatter;

    @Autowired
    public AuthUserService
    (
        Log log,
        AuthUserWriterRepository appSysUserWriterRepository,
        AuthUserReaderRepository sysUserReaderRepository,
        AuthUserValidator authUserValidator,
        NumberFormatter numberFormatter,
        StringFormatter stringFormatter,
        PasswordFormatter passwordFormatter
    ) {
        this.log = log;
        this.authUserValidator = authUserValidator;
        this.sysUserWriterRepository = appSysUserWriterRepository;
        this.sysUserReaderRepository = sysUserReaderRepository;
        this.passwordFormatter = passwordFormatter;
        this.stringFormatter = stringFormatter;
        this.numberFormatter = numberFormatter;
    }

    public AuthedUserDto invoke(AuthUserDto authUserDto) throws Exception {
        authUserValidator.invoke(authUserDto);

        String password = "Abc.1234:)";
        String hashedPassword = passwordFormatter.getHashedPassword(password);
        var newUserEntity = SysUserEntity.getInstance(
            numberFormatter.getNull(),
            stringFormatter.getNull(),
            stringFormatter.getTrimOrNull(authUserDto.codeErp()),
            stringFormatter.getNull(),
            stringFormatter.getTrimOrNull(authUserDto.email()),
            hashedPassword,
            stringFormatter.getTrimOrNull(authUserDto.phone()),
            stringFormatter.getTrimOrNull(authUserDto.fullname()),
            stringFormatter.getTrimOrNull(authUserDto.address()),

            stringFormatter.getTrimOrNull(authUserDto.birthdate()),

            numberFormatter.getIntegerOrNull(authUserDto.idParent()),
            numberFormatter.getIntegerOrNull(authUserDto.idGender()),
            numberFormatter.getIntegerOrNull(authUserDto.idNationality()),
            numberFormatter.getIntegerOrNull(authUserDto.idCountry()),
            numberFormatter.getIntegerOrDefault(authUserDto.idLanguage(), 1),
            numberFormatter.getIntegerOrDefault(authUserDto.idProfile(), 1),

            stringFormatter.getNull(),
            stringFormatter.getNull(),
            numberFormatter.getNull()
        );
        sysUserWriterRepository.createNewUser(newUserEntity);
        var lastId = sysUserWriterRepository.getLastInsertId();
        var dict = sysUserReaderRepository.getUserByUserId(lastId);
        if (dict.isEmpty()) {
            log.debug("not found by id:"+lastId);
            return null;
        }

        return AuthedUserDto.getInstance(
            numberFormatter.getIntegerOrNull(dict.get("id")),

            stringFormatter.getTrimOrNull(dict.get("uuid")),
            stringFormatter.getTrimOrNull(dict.get("code_erp")),
            stringFormatter.getTrimOrNull(dict.get("description")),
            stringFormatter.getTrimOrNull(dict.get("email")),
            stringFormatter.getTrimOrNull(dict.get("secret")),
            stringFormatter.getTrimOrNull(dict.get("phone")),
            stringFormatter.getTrimOrNull(dict.get("fullname")),
            stringFormatter.getTrimOrNull(dict.get("address")),
            stringFormatter.getTrimOrNull(dict.get("birthdate")),

            numberFormatter.getIntegerOrNull(dict.get("id_parent")),
            numberFormatter.getIntegerOrNull(dict.get("id_gender")),
            numberFormatter.getIntegerOrNull(dict.get("id_nationality")),
            numberFormatter.getIntegerOrNull(dict.get("id_country")),
            numberFormatter.getIntegerOrNull(dict.get("id_language")),
            numberFormatter.getIntegerOrNull(dict.get("id_profile")),

            stringFormatter.getTrimOrNull(dict.get("url_picture")),
            stringFormatter.getTrimOrNull(dict.get("date_validated")),

            numberFormatter.getIntegerOrNull(dict.get("log_attempts"))
        );
    }
}