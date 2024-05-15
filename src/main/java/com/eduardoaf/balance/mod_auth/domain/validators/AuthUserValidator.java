package com.eduardoaf.balance.mod_auth.domain.validators;

import com.eduardoaf.balance.mod_auth.application.dtos.AuthUserDto;
import com.eduardoaf.balance.mod_auth.domain.exceptions.AuthUserException;
import com.eduardoaf.balance.mod_auth.infrastructure.repositories.AuthUserReaderRepository;
import com.eduardoaf.balance.mod_shared.domain.enums.LengthsEnum;
import com.eduardoaf.balance.mod_shared.domain.exceptions.DomainTypeException;
import com.eduardoaf.balance.mod_shared.domain.exceptions.DomainValueException;
import com.eduardoaf.balance.mod_shared.domain.validators.AbstractDomainValidator;
import com.eduardoaf.balance.mod_shared.infrastructure.formatters.NumberFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public final class AuthUserValidator extends AbstractDomainValidator {

    private final NumberFormatter numberFormatter;
    private final AuthUserReaderRepository sysUserReaderRepository;
    private AuthUserDto authUserDto;

    @Autowired
    public AuthUserValidator
    (
        NumberFormatter numberFormatter,
        AuthUserReaderRepository sysUserReaderRepository
    ) {
        this.numberFormatter = numberFormatter;
        this.sysUserReaderRepository = sysUserReaderRepository;
    }

    public void invoke(AuthUserDto authUserDto) throws Exception{
        this.authUserDto = authUserDto;

        failIfWrongUsername();
        failIfUserExists();
    }

    private void failIfWrongUsername() throws Exception {
        var label = "Email";
        var value = authUserDto.username();
        if (!isTypeString(value))
            DomainTypeException.valueIsNotString(label, value);

        if (isNullOrEmpty(value))
            DomainValueException.valueIsEmpty(label);

        var length = LengthsEnum.EMAIL.value();
        if (isValueLenGreaterThan(value, length))
            AuthUserException.wrongAccountOrPassword("U001");

        var userId = sysUserReaderRepository.getUsersCredentialsByEmail(value);
        if (userId == null)
            AuthUserException.wrongAccountOrPassword("U002");
    }


    private void failIfUserExists() throws Exception {
        var userId = sysUserReaderRepository.doesUserExistByEmail(authUserDto.username());
        if (userId == null) return;
        var uuid = sysUserReaderRepository.getUuidByUserId(userId);
        AuthUserException.wrongAccountOrPassword(uuid);
    }

}