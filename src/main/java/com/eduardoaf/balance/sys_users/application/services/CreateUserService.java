package com.eduardoaf.balance.sys_users.application.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Autowired
    public CreateUserService
    (
        Log log,
        SysUserWriterRepository appSysUserWriterRepository,
        SysUserReaderRepository sysUserReaderRepository,
        CreateUserValidator createUserValidator
    ) {
        this.log = log;
        this.createUserValidator = createUserValidator;
        this.sysUserWriterRepository = appSysUserWriterRepository;
        this.sysUserReaderRepository = sysUserReaderRepository;
    }

    public CreatedUserDto invoke(CreateUserDto createUserDto) throws Exception {

        createUserValidator.invoke(createUserDto);

        var newUser = SysUserEntity.getInstance(
            createUserDto.codeErp(),
            createUserDto.email(),
            createUserDto.secret(),
            createUserDto.phone(),
            createUserDto.fullname(),
            createUserDto.address(),
            createUserDto.birthdate(),
            createUserDto.idParent(),
            createUserDto.idGender(),
            createUserDto.idNationality(),
            createUserDto.idCountry(),
            createUserDto.idLanguage(),
            createUserDto.idProfile()
        );
        sysUserWriterRepository.createNewUser(newUser);
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
                dict.get("birthdate"),
                Integer.parseInt(dict.get("id_parent")),
                Integer.parseInt(dict.get("id_gender")),
                Integer.parseInt(dict.get("id_nationality")),
                Integer.parseInt(dict.get("id_country")),
                Integer.parseInt(dict.get("id_language")),
                Integer.parseInt(dict.get("id_profile")),
                dict.get("url_picture"),
                dict.get("date_validated"),
                Integer.parseInt(dict.get("log_attempts"))
        );
    }
}