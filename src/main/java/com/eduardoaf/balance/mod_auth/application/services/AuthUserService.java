package com.eduardoaf.balance.mod_auth.application.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eduardoaf.balance.mod_shared.infrastructure.file.Log;
import com.eduardoaf.balance.mod_shared.infrastructure.formatters.NumberFormatter;
import com.eduardoaf.balance.mod_shared.infrastructure.formatters.PasswordFormatter;
import com.eduardoaf.balance.mod_shared.infrastructure.formatters.StringFormatter;
import com.eduardoaf.balance.mod_shared.infrastructure.auth.JwtHelper;

import com.eduardoaf.balance.mod_auth.application.dtos.AuthUserDto;
import com.eduardoaf.balance.mod_auth.infrastructure.repositories.AuthUserReaderRepository;
import com.eduardoaf.balance.mod_auth.infrastructure.repositories.AuthUserWriterRepository;
import com.eduardoaf.balance.mod_auth.application.dtos.AuthedUserDto;

import com.eduardoaf.balance.mod_auth.domain.entities.AuthUserEntity;
import com.eduardoaf.balance.mod_auth.domain.exceptions.AuthUserException;
import com.eduardoaf.balance.mod_auth.domain.validators.AuthUserValidator;

@Service
public final class AuthUserService {

    private final Log log;
    private final AuthUserValidator authUserValidator;
    private final AuthUserWriterRepository sysUserWriterRepository;
    private final AuthUserReaderRepository sysUserReaderRepository;
    private final PasswordFormatter passwordFormatter;
    private final NumberFormatter numberFormatter;
    private final StringFormatter stringFormatter;
    private final JwtHelper jwtHelper;

    private Integer authUserId;

    @Autowired
    public AuthUserService
    (
        Log log,
        AuthUserWriterRepository appBaseUserWriterRepository,
        AuthUserReaderRepository sysUserReaderRepository,
        AuthUserValidator authUserValidator,
        NumberFormatter numberFormatter,
        StringFormatter stringFormatter,
        PasswordFormatter passwordFormatter,
        JwtHelper jwtHelper
    ) {
        this.log = log;
        this.authUserValidator = authUserValidator;
        this.sysUserWriterRepository = appBaseUserWriterRepository;
        this.sysUserReaderRepository = sysUserReaderRepository;
        this.passwordFormatter = passwordFormatter;
        this.stringFormatter = stringFormatter;
        this.numberFormatter = numberFormatter;
        this.jwtHelper = jwtHelper;
    }

    public AuthedUserDto invoke(AuthUserDto authUserDto) throws Exception {

        //se valida
        authUserValidator.invoke(authUserDto);

        var users = sysUserReaderRepository.getUsersCredentialsByEmail(authUserDto.username());
        if (users.isEmpty()) {
            log.debug("not found by email:"+authUserDto.username());
            return null;
        }

        var userMap = users.getFirst();
        String hashedPassword = stringFormatter.getAlwaysString(userMap.get("secret"));
        if (!passwordFormatter.isPasswordValid(authUserDto.password(), hashedPassword)) {
            //update attempt
            AuthUserException.wrongAccountOrPassword("US006");
        }

        authUserId = numberFormatter.getIntegerOrNull(userMap.get("id"));
        userMap = sysUserReaderRepository.getUserByUserId(authUserId);

        var newAuthUserEntity = AuthUserEntity.getInstance(
            numberFormatter.getIntegerOrNull(userMap.get("id")),
            stringFormatter.getTrimOrNull(userMap.get("uuid")),
            stringFormatter.getTrimOrNull(userMap.get("code_erp")),
            stringFormatter.getTrimOrNull(userMap.get("email")),
            stringFormatter.getTrimOrNull(userMap.get("secret")),
            stringFormatter.getTrimOrNull(userMap.get("fullname")),
            numberFormatter.getIntegerOrNull(userMap.get("id_parent")),
            numberFormatter.getIntegerOrNull(userMap.get("id_language")),
            numberFormatter.getIntegerOrNull(userMap.get("id_profile")),
            stringFormatter.getTrimOrNull(userMap.get("url_picture"))
        );

        sysUserWriterRepository.updateUserLogged(newAuthUserEntity);
        var jwtToken = jwtHelper.generateToken(userMap.get("email"));

        return AuthedUserDto.getInstance(
            userMap.get("id"),
            userMap.get("uuid"),
            userMap.get("code_erp"),
            userMap.get("description"),
            userMap.get("email"),
            userMap.get("fullname"),
            userMap.get("id_parent"),
            userMap.get("id_language"),
            userMap.get("id_profile"),
            userMap.get("url_picture"),
            jwtToken
        );
    }
}