package com.eduardoaf.balance.mod_auth.application.services;

import com.eduardoaf.balance.mod_auth.application.dtos.AuthUserDto;
import com.eduardoaf.balance.mod_auth.domain.entities.SysUserEntity;
import com.eduardoaf.balance.mod_auth.infrastructure.repositories.SysUserReaderRepository;
import com.eduardoaf.balance.mod_auth.infrastructure.repositories.SysUserWriterRepository;
import com.eduardoaf.balance.mod_auth.application.dtos.AuthdUserDto;
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
    private final AuthUserValidator createUserValidator;
    private final SysUserWriterRepository sysUserWriterRepository;
    private final SysUserReaderRepository sysUserReaderRepository;
    private final PasswordFormatter passwordFormatter;
    private final NumberFormatter numberFormatter;
    private final StringFormatter stringFormatter;

    @Autowired
    public AuthUserService
    (
        Log log,
        SysUserWriterRepository appSysUserWriterRepository,
        SysUserReaderRepository sysUserReaderRepository,
        AuthUserValidator createUserValidator,
        NumberFormatter numberFormatter,
        StringFormatter stringFormatter,
        PasswordFormatter passwordFormatter
    ) {
        this.log = log;
        this.createUserValidator = createUserValidator;
        this.sysUserWriterRepository = appSysUserWriterRepository;
        this.sysUserReaderRepository = sysUserReaderRepository;
        this.passwordFormatter = passwordFormatter;
        this.stringFormatter = stringFormatter;
        this.numberFormatter = numberFormatter;
    }

    public AuthdUserDto invoke(AuthUserDto createUserDto) throws Exception {
        createUserValidator.invoke(createUserDto);

        String password = "Abc.1234:)";
        String hashedPassword = passwordFormatter.getHashedPassword(password);
        var newUserEntity = SysUserEntity.getInstance(
            numberFormatter.getNull(),
            stringFormatter.getNull(),
            stringFormatter.getTrimOrNull(createUserDto.codeErp()),
            stringFormatter.getNull(),
            stringFormatter.getTrimOrNull(createUserDto.email()),
            hashedPassword,
            stringFormatter.getTrimOrNull(createUserDto.phone()),
            stringFormatter.getTrimOrNull(createUserDto.fullname()),
            stringFormatter.getTrimOrNull(createUserDto.address()),

            stringFormatter.getTrimOrNull(createUserDto.birthdate()),

            numberFormatter.getIntegerOrNull(createUserDto.idParent()),
            numberFormatter.getIntegerOrNull(createUserDto.idGender()),
            numberFormatter.getIntegerOrNull(createUserDto.idNationality()),
            numberFormatter.getIntegerOrNull(createUserDto.idCountry()),
            numberFormatter.getIntegerOrDefault(createUserDto.idLanguage(), 1),
            numberFormatter.getIntegerOrDefault(createUserDto.idProfile(), 1),

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

        return AuthdUserDto.getInstance(
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