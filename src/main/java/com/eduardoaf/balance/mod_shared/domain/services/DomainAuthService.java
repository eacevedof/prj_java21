package com.eduardoaf.balance.mod_shared.domain.services;

import com.eduardoaf.balance.mod_users.domain.entities.BaseUserEntity;
import com.eduardoaf.balance.mod_shared.infrastructure.auth.JwtHelper;

import com.eduardoaf.balance.mod_shared.domain.exceptions.AuthUserException;
import com.eduardoaf.balance.mod_users.infrastructure.repositories.BaseUserReaderRepository;


import org.springframework.stereotype.Service;

@Service
public class DomainAuthService {

    private final BaseUserReaderRepository sysUserReaderRepository;
    private BaseUserEntity baseUserEntity;
    private final JwtHelper jwtHelper;

    public DomainAuthService (
            BaseUserReaderRepository sysUserWriterRepository,
            JwtHelper jwtHelper
    ) {
        this.sysUserReaderRepository = sysUserWriterRepository;
        this.jwtHelper = jwtHelper;
        this.baseUserEntity = null;
    }

    public void tryToLoadAuthUserByJwt(
        String jwtToken
    ) throws Exception {

        var username = jwtHelper.getUsernameByJwt(jwtToken);
        Integer userId = sysUserReaderRepository.getUserIdByEmail(username);
        if (userId == null) {
            return;
        }
        baseUserEntity = sysUserReaderRepository.getUserEntityByUserId(userId);
        if (baseUserEntity == null) {
            AuthUserException.unauthorizedUser(jwtToken);
        }
    }

    public BaseUserEntity getAuthUser() {
        return baseUserEntity;
    }
}
