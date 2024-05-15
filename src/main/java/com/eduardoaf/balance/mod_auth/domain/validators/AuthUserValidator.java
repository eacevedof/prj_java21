package com.eduardoaf.balance.mod_auth.domain.validators;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eduardoaf.balance.mod_auth.application.dtos.AuthUserDto;
import com.eduardoaf.balance.mod_auth.domain.exceptions.AuthUserException;
import com.eduardoaf.balance.mod_auth.infrastructure.repositories.AuthUserReaderRepository;

import com.eduardoaf.balance.mod_shared.domain.enums.LengthsEnum;
import com.eduardoaf.balance.mod_shared.domain.exceptions.DomainTypeException;
import com.eduardoaf.balance.mod_shared.domain.exceptions.DomainValueException;
import com.eduardoaf.balance.mod_shared.domain.validators.AbstractDomainValidator;
import com.eduardoaf.balance.mod_shared.infrastructure.formatters.NumberFormatter;
import com.eduardoaf.balance.mod_shared.infrastructure.formatters.StringFormatter;

@Service
public final class AuthUserValidator extends AbstractDomainValidator {

    private final StringFormatter stringFormatter;
    private final NumberFormatter numberFormatter;
    private final AuthUserReaderRepository sysUserReaderRepository;
    private AuthUserDto authUserDto;
    private List<Map<String, String>> users;

    @Autowired
    public AuthUserValidator
    (
        StringFormatter stringFormatter,
        NumberFormatter numberFormatter,
        AuthUserReaderRepository sysUserReaderRepository
    ) {
        this.stringFormatter = stringFormatter;
        this.numberFormatter = numberFormatter;
        this.sysUserReaderRepository = sysUserReaderRepository;
    }

    public void invoke(AuthUserDto authUserDto) throws Exception{
        this.authUserDto = authUserDto;

        failIfWrongUsername();
        failIfUserIsLocked();
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

        users = sysUserReaderRepository.getUsersCredentialsByEmail(value);
        if (users.isEmpty())
            AuthUserException.wrongAccountOrPassword("U002");

        if (users.size()>1)
            AuthUserException.wrongAccountOrPassword("U003");
    }

    private void failIfUserIsLocked() throws Exception {
        var user = users.getFirst();
        String deleteDate = stringFormatter.getEmptyIfNull(user.get("delete_date"));
        if (!deleteDate.isEmpty())
            AuthUserException.wrongAccountOrPassword("U004");
        var isEnabled = numberFormatter.getBoolean(user.get("is_enabled"));
        if (!isEnabled)
            AuthUserException.wrongAccountOrPassword("U005");
    }

}