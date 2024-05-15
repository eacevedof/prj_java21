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

    public CreatedUserDto invoke(
        CreateUserDto createUserDto
    ) throws Exception {
        createUserValidator.invoke(createUserDto);

        String password = "Abc.1234:)";
        String hashedPassword = passwordFormatter.getHashedPassword(password);
        var newUserEntity = BaseUserEntity.fromStrings(
            stringFormatter.getNull(),
            stringFormatter.getNull(),
            createUserDto.codeErp(),
            stringFormatter.getNull(),
            createUserDto.email(),
            hashedPassword,
            createUserDto.phone(),
            createUserDto.fullname(),
            createUserDto.address(),

            createUserDto.birthdate(),

            createUserDto.idParent(),
            createUserDto.idGender(),
            createUserDto.idNationality(),
            createUserDto.idCountry(),
            stringFormatter.getTrimOrDefault(createUserDto.idLanguage(), "1"),
            stringFormatter.getTrimOrDefault(createUserDto.idProfile(), "1"),
            stringFormatter.getNull(),
            stringFormatter.getNull(),
            stringFormatter.getNull(),
            createUserDto.idAuthUser(),
            createUserDto.idPlatform()
        );
        sysUserWriterRepository.createNewUser(newUserEntity);
        Integer lastId = sysUserWriterRepository.getLastInsertId();

        var baseUserEntity = sysUserReaderRepository.getUserEntityByUserId(lastId);
        if (baseUserEntity == null) {
            log.debug("not found by id:"+lastId);
            return null;
        }
        return CreatedUserDto.fromBaseUserEntity(
            baseUserEntity
        );
    }
}