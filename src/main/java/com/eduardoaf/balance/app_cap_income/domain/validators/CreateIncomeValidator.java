package com.eduardoaf.balance.app_cap_income.domain.validators;

import com.eduardoaf.balance.shared.domain.exceptions.TypeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eduardoaf.balance.shared.domain.validators.AbstractValidator;
import com.eduardoaf.balance.app_cap_income.application.dtos.CreateIncomeDto;
import com.eduardoaf.balance.app_cap_income.infrastructure.repositories.AppCapIncomeReaderRepository;

import com.eduardoaf.balance.shared.domain.exceptions.ValueException;
import com.eduardoaf.balance.shared.domain.enums.LengthsEnum;

@Service
public final class CreateIncomeValidator extends AbstractValidator {

    private final AppCapIncomeReaderRepository appCapIncomeReaderRepository;
    private CreateIncomeDto createIncomeDto;

    @Autowired
    public CreateIncomeValidator
    (
        AppCapIncomeReaderRepository appCapIncomeReaderRepository
    ) {
        this.appCapIncomeReaderRepository = appCapIncomeReaderRepository;
    }

    public void invoke(CreateIncomeDto createIncomeDto) throws Exception {
        this.createIncomeDto = createIncomeDto;
        failIfWrongPaymentFor();
        failIfWrongCodeErp();
        failIfWrongDescription();
        failIfWrongPayedFrom();
    }

    private void failIfWrongPaymentFor() throws TypeException, ValueException {
        var label = "Payment for";
        var value = createIncomeDto.paymentFor();
        if (!isTypeString(value))
            TypeException.valueIsNotString(label, value);

        var length = LengthsEnum.PAYMENT_FOR.value();
        if (isLengthGreaterThan(value, length))
            ValueException.wrongMaxLength(label, value, length);
    }

    private void failIfWrongCodeErp() throws ValueException {
        var label = "Code ERP";
        var value = createIncomeDto.codeErp();
        var length = LengthsEnum.CODE_ERP.value();
        if (isLengthGreaterThan(value, length))
            ValueException.wrongMaxLength(label, value, length);
    }

    private void failIfWrongDescription() throws TypeException, ValueException {
        var label = "Description";
        var value = createIncomeDto.description();
        if (!isTypeString(value))
            TypeException.valueIsNotString(label, value);

        var length = LengthsEnum.DESCRIPTION.value();
        if (isLengthGreaterThan(value, length))
            ValueException.wrongMaxLength(label, value, length);
    }

    private void failIfWrongPayedFrom() throws TypeException, ValueException {
        var label = "Payed from";
        var value = createIncomeDto.payedFrom();
        if (!isTypeString(value))
            TypeException.valueIsNotString(label, value);

        var length = LengthsEnum.PAYED_FROM.value();
        if (isLengthGreaterThan(value, length))
            ValueException.wrongMaxLength(label, value, length);
    }
}