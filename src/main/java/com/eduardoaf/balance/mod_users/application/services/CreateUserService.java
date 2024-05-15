package com.eduardoaf.balance.mod_users.application.services;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.eduardoaf.balance.mod_shared.infrastructure.formatters.NumberFormatter;
import com.eduardoaf.balance.mod_shared.infrastructure.formatters.PasswordFormatter;
import com.eduardoaf.balance.mod_shared.infrastructure.formatters.StringFormatter;
import com.eduardoaf.balance.mod_shared.infrastructure.file.Log;
import com.eduardoaf.balance.mod_users.infrastructure.repositories.BaseUserReaderRepository;
import com.eduardoaf.balance.mod_users.infrastructure.repositories.BaseUserWriterRepository;
import com.eduardoaf.balance.mod_users.application.dtos.CreateUserDto;
import com.eduardoaf.balance.mod_users.application.dtos.CreatedUserDto;
import com.eduardoaf.balance.mod_users.domain.validators.CreateUserValidator;
import com.eduardoaf.balance.mod_users.domain.entities.BaseUserEntity;

@Service
public final class CreateUserService {

    private final Log log;
    private final CreateUserValidator createUserValidator;
    private final BaseUserWriterRepository sysUserWriterRepository;
    private final BaseUserReaderRepository sysUserReaderRepository;
    private final PasswordFormatter passwordFormatter;
    private final NumberFormatter numberFormatter;
    private final StringFormatter stringFormatter;

    @Autowired
    public CreateUserService
    (
        Log log,
        BaseUserWriterRepository appBaseUserWriterRepository,
        com.eduardoaf.balance.mod_users.infrastructure.repositories.BaseUserReaderRepository sysUserReaderRepository,
        CreateUserValidator createUserValidator,
        NumberFormatter numberFormatter,
        StringFormatter stringFormatter,
        PasswordFormatter passwordFormatter
    ) {
        this.log = log;
        this.createUserValidator = createUserValidator;
        this.sysUserWriterRepository = appBaseUserWriterRepository;
        this.sysUserReaderRepository = sysUserReaderRepository;
        this.passwordFormatter = passwordFormatter;
        this.stringFormatter = stringFormatter;
        this.numberFormatter = numberFormatter;
    }

    public CreatedUserDto invoke(CreateUserDto createUserDto) throws Exception {
        createUserValidator.invoke(createUserDto);

        String password = "Abc.1234:)";
        String hashedPassword = passwordFormatter.getHashedPassword(password);
        var newUserEntity = com.eduardoaf.balance.mod_users.domain.entities.BaseUserEntity.getInstance(
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

        return CreatedUserDto.getInstance(
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