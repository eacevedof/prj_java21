package com.eduardoaf.balance.app_cap_income.application.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eduardoaf.balance.app_cap_income.application.dtos.CreateIncomeDto;
import com.eduardoaf.balance.app_cap_income.infrastructure.repositories.AppCapIncomeReaderRepository;
import com.eduardoaf.balance.app_cap_income.application.exceptions.CreateIncomeException;

@Service
public final class CreateIncomeValidator {

    private final AppCapIncomeReaderRepository appCapIncomeReaderRepository;
    private CreateIncomeDto createIncomeDto;

    @Autowired
    public CreateIncomeValidator
    (
        AppCapIncomeReaderRepository appCapIncomeReaderRepository
    ) {
        this.appCapIncomeReaderRepository = appCapIncomeReaderRepository;
    }

    public void invoke(CreateIncomeDto createIncomeDto) throws CreateIncomeException {
        this.createIncomeDto = createIncomeDto;
        validateCodeErp();
    }

    private void validateCodeErp() throws CreateIncomeException {
        if (createIncomeDto.codeErp().length() > 50) {
            CreateIncomeException.errorOnCodeErpLength();
        }
    }
}