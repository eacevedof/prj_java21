package com.eduardoaf.balance.mod_shared.domain.services;

import org.springframework.stereotype.Service;

import com.eduardoaf.balance.mod_shared.infrastructure.file.Log;
import com.eduardoaf.balance.mod_users.domain.entities.BaseUserEntity;
import com.eduardoaf.balance.mod_shared.infrastructure.auth.JwtHelper;

import com.eduardoaf.balance.mod_shared.domain.exceptions.AuthUserException;
import com.eduardoaf.balance.mod_users.infrastructure.repositories.BaseUserReaderRepository;


@Service
public class DomainAuthService {

    private final Log log;
    private final BaseUserReaderRepository sysUserReaderRepository;
    private static BaseUserEntity baseUserEntity;
    private final JwtHelper jwtHelper;

    public DomainAuthService (
        Log log,
        BaseUserReaderRepository sysUserWriterRepository,
        JwtHelper jwtHelper
    ) {
        this.log = log;
        this.sysUserReaderRepository = sysUserWriterRepository;
        this.jwtHelper = jwtHelper;
    }

    public void tryToLoadAuthUserByJwtOrFail(
        String jwtToken
    ) throws Exception {
        DomainAuthService.baseUserEntity = null;
        jwtToken = getBearerToken(jwtToken);
        if (jwtToken.isEmpty()) {
            AuthUserException.unauthorizedUser(jwtToken);
        }

        String username = "";
        try {
            username = jwtHelper.getUsernameByJwt(jwtToken);
        }
        catch (Exception e) {
            log.exception(e);
            AuthUserException.unauthorizedUser(jwtToken);
        }

        Integer userId = sysUserReaderRepository.getUserIdByEmail(username);
        if (userId == null) {
            AuthUserException.unauthorizedUser(jwtToken);
        }
        DomainAuthService.baseUserEntity = sysUserReaderRepository.getUserMinByUserId(userId);
        if (baseUserEntity == null)
            AuthUserException.unauthorizedUser("not found");

        if (baseUserEntity.getDeleteDate() != null && !baseUserEntity.getDeleteDate().isEmpty())
            AuthUserException.unauthorizedUser("deleted user");

        if (baseUserEntity.getIsEnabled().equals("0"))
            AuthUserException.unauthorizedUser( "disabled user");
    }

    private String getBearerToken(
        String jwtToken
    ) {
        if (jwtToken == null) return "";
        return jwtToken.substring(7);
    }

    public BaseUserEntity getAuthUser() {
        return DomainAuthService.baseUserEntity;
    }

    public Integer getIntegerAuthUserId() {
        return DomainAuthService.baseUserEntity.getId();
    }

    public String getStringAuthUserId() {
        if (DomainAuthService.baseUserEntity.getId() == null)
            return null;
        return DomainAuthService.baseUserEntity.getId().toString();
    }
}
