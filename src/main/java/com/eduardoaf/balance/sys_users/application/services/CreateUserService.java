package com.eduardoaf.balance.sys_users.application.services;

import com.eduardoaf.balance.shared.infrastructure.formatters.NumberFormatter;
import com.eduardoaf.balance.shared.infrastructure.formatters.PasswordFormatter;
import com.eduardoaf.balance.shared.infrastructure.formatters.StringFormatter;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.eduardoaf.balance.shared.infrastructure.file.Log;
import com.eduardoaf.balance.sys_users.infrastructure.repositories.SysUserReaderRepository;
import com.eduardoaf.balance.sys_users.infrastructure.repositories.SysUserWriterRepository;
import com.eduardoaf.balance.sys_users.application.dtos.CreateUserDto;
import com.eduardoaf.balance.sys_users.application.dtos.CreatedUserDto;
import com.eduardoaf.balance.sys_users.domain.validators.CreateUserValidator;
import com.eduardoaf.balance.sys_users.domain.entities.SysUserEntity;

@Service
public final class CreateUserService {

    private final Log log;
    private final CreateUserValidator createUserValidator;
    private final SysUserWriterRepository sysUserWriterRepository;
    private final SysUserReaderRepository sysUserReaderRepository;
    private final PasswordFormatter passwordFormatter;
    private final NumberFormatter numberFormatter;
    private final StringFormatter stringFormatter;

    @Autowired
    public CreateUserService
    (
        Log log,
        SysUserWriterRepository appSysUserWriterRepository,
        SysUserReaderRepository sysUserReaderRepository,
        CreateUserValidator createUserValidator,
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

    public CreatedUserDto invoke(CreateUserDto createUserDto) throws Exception {
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

            numberFormatter.getAsInteger(createUserDto.idParent()),
            numberFormatter.getAsInteger(createUserDto.idGender()),
            numberFormatter.getAsInteger(createUserDto.idNationality()),
            numberFormatter.getAsInteger(createUserDto.idCountry()),
            numberFormatter.getAsInteger(createUserDto.idLanguage(), 1),
            numberFormatter.getAsInteger(createUserDto.idProfile(), 1),

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
            numberFormatter.getAsInteger(dict.get("id")),

            stringFormatter.getTrimOrNull(dict.get("uuid")),
            stringFormatter.getTrimOrNull(dict.get("code_erp")),
            stringFormatter.getTrimOrNull(dict.get("description")),
            stringFormatter.getTrimOrNull(dict.get("email")),
            stringFormatter.getTrimOrNull(dict.get("secret")),
            stringFormatter.getTrimOrNull(dict.get("phone")),
            stringFormatter.getTrimOrNull(dict.get("fullname")),
            stringFormatter.getTrimOrNull(dict.get("address")),
            stringFormatter.getTrimOrNull(dict.get("birthdate")),

            numberFormatter.getAsInteger(dict.get("id_parent")),
            numberFormatter.getAsInteger(dict.get("id_gender")),
            numberFormatter.getAsInteger(dict.get("id_nationality")),
            numberFormatter.getAsInteger(dict.get("id_country")),
            numberFormatter.getAsInteger(dict.get("id_language")),
            numberFormatter.getAsInteger(dict.get("id_profile")),

            stringFormatter.getTrimOrNull(dict.get("url_picture")),
            stringFormatter.getTrimOrNull(dict.get("date_validated")),

            numberFormatter.getAsInteger(dict.get("log_attempts"))
        );
    }
}