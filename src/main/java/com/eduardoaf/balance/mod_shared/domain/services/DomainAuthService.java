package com.eduardoaf.balance.mod_shared.domain.services;

import com.eduardoaf.balance.mod_users.domain.entities.BaseUserEntity;
import com.eduardoaf.balance.mod_shared.infrastructure.auth.JwtHelper;

import com.eduardoaf.balance.mod_users.infrastructure.repositories.SysUserReaderRepository;
import com.eduardoaf.balance.mod_users.infrastructure.repositories.SysUserWriterRepository;
import org.springframework.stereotype.Service;

@Service
public class DomainAuthService {

    private final SysUserReaderRepository sysUserReaderRepository;
    private BaseUserEntity baseUserEntity;
    private final JwtHelper jwtHelper;

    public DomainAuthService (
            SysUserReaderRepository sysUserWriterRepository,
            JwtHelper jwtHelper
    ) {
        this.sysUserReaderRepository = sysUserWriterRepository;
        this.jwtHelper = jwtHelper;
        this.baseUserEntity = null;
    }


    public void tryToLoadAuthUserByJwt(String jwtToken) throws Exception {

        var username = jwtHelper.getUsernameByJwt(jwtToken);

        Integer userId = sysUserReaderRepository.getUserIdByEmail(username);
        if (userId == null) {
            return;
        }
        var user = sysUserReaderRepository.getUserByUserId(userId);


        JwtHelper jwtHelper = JwtHelper.getInstance();
        baseUserEntity = jwtHelper.getBaseUserEntity(jwtToken);
    }

    public boolean isUserAuthenticated(String token) {
        return true;
    }

    public BaseUserEntity getAuthUser() {
        return baseUserEntity;
    }
}
