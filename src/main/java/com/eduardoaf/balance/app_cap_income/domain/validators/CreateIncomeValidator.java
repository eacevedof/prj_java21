package com.eduardoaf.balance.app_cap_income.domain.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eduardoaf.balance.shared.domain.exceptions.DomainTypeException;
import com.eduardoaf.balance.shared.domain.validators.AbstractDomainValidator;
import com.eduardoaf.balance.app_cap_income.application.dtos.CreateIncomeDto;
import com.eduardoaf.balance.app_cap_income.infrastructure.repositories.AppCapIncomeReaderRepository;

import com.eduardoaf.balance.shared.domain.exceptions.DomainValueException;
import com.eduardoaf.balance.shared.domain.enums.LengthsEnum;

@Service
public final class CreateIncomeValidator extends AbstractDomainValidator {

    private final AppCapIncomeReaderRepository appCapIncomeReaderRepository;
    private CreateIncomeDto createIncomeDto;

    @Autowired
    public CreateIncomeValidator
    (
        AppCapIncomeReaderRepository appCapIncomeReaderRepository
    ) {
        this.appCapIncomeReaderRepository = appCapIncomeReaderRepository;
    }

    public void invoke(CreateIncomeDto createIncomeDto) throws DomainTypeException, DomainValueException {
        this.createIncomeDto = createIncomeDto;
        failIfWrongPaymentFor();
        failIfWrongCodeErp();
        failIfWrongDescription();
        failIfWrongPayedFrom();
        failIfWrongIncomeDate();
        failIfWrongAmount();
        failIfWrongNotes();
    }

    private void failIfWrongPaymentFor() throws DomainTypeException, DomainValueException {
        var label = "Payment for";
        var value = createIncomeDto.paymentFor();
        if (!isTypeString(value))
            DomainTypeException.valueIsNotString(label, value);

        var length = LengthsEnum.PAYMENT_FOR.value();
        if (isValueLenGreaterThan(value, length))
            DomainValueException.wrongMaxLength(label, value, length);
    }

    private void failIfWrongCodeErp() throws DomainValueException {
        var label = "Code ERP";
        var value = createIncomeDto.codeErp();
        var length = LengthsEnum.CODE_ERP.value();
        if (isValueLenGreaterThan(value, length))
            DomainValueException.wrongMaxLength(label, value, length);
    }

    private void failIfWrongDescription() throws DomainTypeException, DomainValueException {
        var label = "Description";
        var value = createIncomeDto.description();
        if (!isTypeString(value))
            DomainTypeException.valueIsNotString(label, value);

        var length = LengthsEnum.DESCRIPTION.value();
        if (isValueLenGreaterThan(value, length))
            DomainValueException.wrongMaxLength(label, value, length);
    }

    private void failIfWrongPayedFrom() throws DomainTypeException, DomainValueException {
        var label = "Payed from";
        var value = createIncomeDto.payedFrom();
        if (!isTypeString(value))
            DomainTypeException.valueIsNotString(label, value);

        if (isNullOrEmpty(value))
            DomainValueException.valueIsEmpty(label);

        var length = LengthsEnum.PAYED_FROM.value();
        if (isValueLenGreaterThan(value, length))
            DomainValueException.wrongMaxLength(label, value, length);
    }

    private void failIfWrongIncomeDate() throws DomainTypeException, DomainValueException {
        var label = "Income date";
        var value = createIncomeDto.incomeDate();
        if (!isTypeString(value))
            DomainTypeException.valueIsNotString(label, value);

        if (isNullOrEmpty(value))
            DomainValueException.valueIsEmpty(label);

        var length = LengthsEnum.INCOME_DATE.value();
        if (isValueLenGreaterThan(value, length))
            DomainValueException.wrongMaxLength(label, value, length);

        if (!isTypeDate(value))
            DomainValueException.wrongDateFormat(label, value, "yyyyy-mm-dd");

    }

    private void failIfWrongAmount() throws DomainTypeException, DomainValueException {
        var label = "Amount";
        var value = createIncomeDto.amount();
        if (!isTypeString(value))
            DomainTypeException.valueIsNotString(label, value);

        if (isNullOrEmpty(value))
            DomainValueException.valueIsEmpty(label);

        if (!isTypeNumeric(value))
            DomainTypeException.valueIsNotNumeric(label, value);

        if (isValueLowerThan(value, 0))
            DomainValueException.valueIsLowerThanMinimum(label, value, 0);

        var length = LengthsEnum.AMOUNT.value();
        if (isValueLenGreaterThan(value, length))
            DomainValueException.wrongMaxLength(label, value, length);
    }

    private void failIfWrongNotes() throws DomainTypeException, DomainValueException {
        var label = "Notes";
        var value = createIncomeDto.notes();
        if (!isTypeString(value))
            DomainTypeException.valueIsNotString(label, value);

        var length = LengthsEnum.NOTES.value();
        if (isValueLenGreaterThan(value, length))
            DomainValueException.wrongMaxLength(label, value, length);
    }
}