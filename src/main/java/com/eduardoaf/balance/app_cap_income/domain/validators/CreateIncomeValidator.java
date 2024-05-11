package com.eduardoaf.balance.app_cap_income.domain.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eduardoaf.balance.shared.domain.validators.AbstractValidator;
import com.eduardoaf.balance.app_cap_income.application.dtos.CreateIncomeDto;
import com.eduardoaf.balance.app_cap_income.infrastructure.repositories.AppCapIncomeReaderRepository;
import com.eduardoaf.balance.app_cap_income.application.exceptions.CreateIncomeException;

import com.eduardoaf.balance.shared.domain.exceptions.TypeException;
import com.eduardoaf.balance.shared.domain.exceptions.ValueException;

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
        validateCodeErp();
    }

    private void validateCodeErp() throws ValueException {
        if (isLengthGreaterThan(createIncomeDto.codeErp(), 50))
            ValueException.maxLength("Code Erp", createIncomeDto.codeErp(), 50);

    }
}