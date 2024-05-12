package com.eduardoaf.balance.sys_users.domain.validators;

import com.eduardoaf.balance.shared.domain.enums.LengthsEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eduardoaf.balance.shared.infrastructure.formatters.NumberFormatter;
import com.eduardoaf.balance.shared.domain.exceptions.DomainTypeException;
import com.eduardoaf.balance.shared.domain.validators.AbstractDomainValidator;
import com.eduardoaf.balance.shared.domain.exceptions.DomainValueException;
import com.eduardoaf.balance.sys_users.application.dtos.CreateUserDto;
import com.eduardoaf.balance.sys_users.infrastructure.repositories.SysUserReaderRepository;
import com.eduardoaf.balance.sys_users.domain.exceptions.CreateUserException;

@Service
public final class CreateUserValidator extends AbstractDomainValidator {

    private final NumberFormatter numberFormatter;
    private final SysUserReaderRepository sysUserReaderRepository;
    private CreateUserDto createUserDto;

    @Autowired
    public CreateUserValidator
    (
        NumberFormatter numberFormatter,
        SysUserReaderRepository sysUserReaderRepository
    ) {
        this.numberFormatter = numberFormatter;
        this.sysUserReaderRepository = sysUserReaderRepository;
    }

    public void invoke(CreateUserDto createUserDto) throws Exception{
        this.createUserDto = createUserDto;
        failIfWrongEmail();


        failIfWrongIdLanguage();
        failIfWrongIdProfile();
        failIfUserExists();
    }

    private void failIfUserExists() throws Exception {
        var userId = sysUserReaderRepository.doesUserExistByEmail(createUserDto.email());
        if (userId == 0) return;
        var uuid = sysUserReaderRepository.getUuidByUserId(userId);
        CreateUserException.userAlreadyExists(uuid);
    }

    private void failIfWrongEmail() throws Exception {
        var label = "Email";
        var value = createUserDto.email();
        if (!isTypeString(value))
            DomainTypeException.valueIsNotString(label, value);

        if (isNullOrEmpty(value))
            DomainValueException.valueIsEmpty(label);

        var length = LengthsEnum.EMAIL.value();
        if (isValueLenGreaterThan(value, length))
            DomainValueException.wrongMaxLength(label, value, length);

        if (!isValueEmail(value))
            DomainValueException.wrongEmailFormat(label, value, "username@domain.xxx");

        var userId = sysUserReaderRepository.getUserIdByEmail(value);
        if (userId == null)
            CreateUserException.userAlreadyExists(value);
    }

    private void failIfWrongIdLanguage() throws DomainTypeException, DomainValueException {
        var label = "Language ID";
        var value = createUserDto.idLanguage();
        if (!isTypeInteger(value))
            DomainTypeException.valueIsNotNumeric(label, value);

        if (isValueLowerThan(value, 0))
            DomainValueException.valueIsLowerThanMinimum(label, value, 0);
    }

    private void failIfWrongIdProfile() throws DomainTypeException, DomainValueException {
        var label = "Profile ID";
        var value = createUserDto.idProfile();
        if (!isTypeNumeric(value))
            DomainTypeException.valueIsNotNumeric(label, value);

        if (isValueLowerThan(value, 0))
            DomainValueException.valueIsLowerThanMinimum(label, value, 0);
    }
}