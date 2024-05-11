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

    }

    private void failIfWrongPaymentFor() throws TypeException {
        if (!isTypeString(createIncomeDto.paymentFor())) {
            TypeException.valueIsNotString("Payment for", createIncomeDto.paymentFor());
        }
    }

    private void failIfWrongCodeErp() throws ValueException {
        if (isLengthGreaterThan(createIncomeDto.codeErp(), 50))
            ValueException.wrongMaxLength(
                "Code Erp",
                createIncomeDto.codeErp(),
                LengthsEnum.CODE_ERP.value()
            );

    }
}