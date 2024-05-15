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
    private final AuthUserWriterRepository authUserWriterRepository;
    private final AuthUserReaderRepository authUserReaderRepository;
    private final PasswordFormatter passwordFormatter;
    private final NumberFormatter numberFormatter;
    private final StringFormatter stringFormatter;
    private final JwtHelper jwtHelper;

    private Integer authUserId;

    @Autowired
    public AuthUserService
    (
        Log log,
        AuthUserWriterRepository authUserWriterRepository,
        AuthUserReaderRepository authUserReaderRepository,
        AuthUserValidator authUserValidator,
        NumberFormatter numberFormatter,
        StringFormatter stringFormatter,
        PasswordFormatter passwordFormatter,
        JwtHelper jwtHelper
    ) {
        this.log = log;
        this.authUserValidator = authUserValidator;
        this.authUserWriterRepository = authUserWriterRepository;
        this.authUserReaderRepository = authUserReaderRepository;
        this.passwordFormatter = passwordFormatter;
        this.stringFormatter = stringFormatter;
        this.numberFormatter = numberFormatter;
        this.jwtHelper = jwtHelper;
    }

    public AuthedUserDto invoke(
        AuthUserDto authUserDto
    ) throws Exception {

        authUserValidator.invoke(authUserDto);

        var users = authUserReaderRepository.getUsersCredentialsByEmail(authUserDto.username());
        if (users.isEmpty()) {
            log.debug("not found by email: "+authUserDto.username());
            return null;
        }

        var userMap = users.getFirst();
        //esta entidad debe crearse con los datos del dto platform
        var authUserEntity = AuthUserEntity.fromMapRow(userMap);
        String hashedPassword = stringFormatter.getAlwaysString(authUserEntity.getSecret());

        if (!passwordFormatter.isPasswordValid(authUserDto.password(), hashedPassword)) {
            authUserWriterRepository.updateLogAttemptByEmail(authUserEntity);
            AuthUserException.wrongAccountOrPassword("US006");
        }

        authUserId = numberFormatter.getIntegerOrNull(userMap.get("id"));
        userMap = authUserReaderRepository.getUserByUserId(authUserId);
        authUserEntity = AuthUserEntity.fromMapRow(userMap);

        authUserWriterRepository.updateUserLogged(authUserEntity);
        var jwtToken = jwtHelper.generateToken(authUserEntity.getEmail());

        return AuthedUserDto.fromAuthUserEntityAndJwt(
            authUserEntity,
            jwtToken
        );
    }
}