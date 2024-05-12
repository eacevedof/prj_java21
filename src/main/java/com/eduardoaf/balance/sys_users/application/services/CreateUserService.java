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
            null,
            null,
            createUserDto.codeErp(),
            null,
            createUserDto.email(),
            hashedPassword,
            createUserDto.phone(),
            createUserDto.fullname(),
            createUserDto.address(),

            stringFormatter.getTrimOrNull(createUserDto.birthdate()),


            createUserDto.idParent() == "" ? null : Integer.parseInt(createUserDto.idParent()),
            createUserDto.idGender() == "" ? null : Integer.parseInt(createUserDto.idGender()),
            createUserDto.idNationality() == "" ? null : Integer.parseInt(createUserDto.idNationality()),
            createUserDto.idCountry() == "" ? null : Integer.parseInt(createUserDto.idCountry()),
            createUserDto.idLanguage() == "" ? 1 : Integer.parseInt(createUserDto.idLanguage()),
            createUserDto.idProfile() == "" ? 1 : Integer.parseInt(createUserDto.idProfile()),

            null,
            null,
            0
        );
        sysUserWriterRepository.createNewUser(newUserEntity);
        var lastId = sysUserWriterRepository.getLastInsertId();
        var dict = sysUserReaderRepository.getUserByUserId(lastId);
        if (dict.isEmpty()) {
            log.debug("not found by id:"+lastId);
            return null;
        }

        return CreatedUserDto.getInstance(
            Integer.parseInt(dict.get("id")),

            dict.get("uuid"),
            dict.get("code_erp"),
            dict.get("description"),
            dict.get("email"),
            dict.get("secret"),
            dict.get("phone"),
            dict.get("fullname"),
            dict.get("address"),
            dict.get("birthdate") == null ? null : dict.get("birthdate"),

            numberFormatter.getAsInteger(dict.get("id_parent")),
            numberFormatter.getAsInteger(dict.get("id_gender")),
            numberFormatter.getAsInteger(dict.get("id_nationality")),
            numberFormatter.getAsInteger(dict.get("id_country")),
            numberFormatter.getAsInteger(dict.get("id_language")),
            numberFormatter.getAsInteger(dict.get("id_profile")),

            dict.get("url_picture"),
            dict.get("date_validated"),
            numberFormatter.getAsInteger(dict.get("log_attempts"))
        );
    }
}