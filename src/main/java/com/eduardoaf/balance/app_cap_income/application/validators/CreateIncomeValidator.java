package com.eduardoaf.balance.app_cap_income.application.validators;

import com.eduardoaf.balance.app_cap_income.application.dtos.CreateIncomeDto;
import com.eduardoaf.balance.app_cap_income.application.dtos.CreatedIncomeDto;
import com.eduardoaf.balance.app_cap_income.domain.entities.AppCapIncomeEntity;
import com.eduardoaf.balance.app_cap_income.infrastructure.repositories.AppCapIncomeReaderRepository;
import com.eduardoaf.balance.app_cap_income.infrastructure.repositories.AppCapIncomeWriterRepository;
import com.eduardoaf.balance.shared.infrastructure.file.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public final class CreateIncomeValidator {

    private final AppCapIncomeWriterRepository appCapIncomeWriterRepository;
    private final AppCapIncomeReaderRepository appCapIncomeReaderRepository;
    private CreateIncomeDto createIncomeDto;

    @Autowired
    public CreateIncomeValidator
    (
        AppCapIncomeWriterRepository appCapIncomeWriterRepository,
        AppCapIncomeReaderRepository appCapIncomeReaderRepository
    ) {
        this.appCapIncomeWriterRepository = appCapIncomeWriterRepository;
        this.appCapIncomeReaderRepository = appCapIncomeReaderRepository;
    }

    public CreatedIncomeDto invoke(CreateIncomeDto createIncomeDto) throws Exception {
        this.createIncomeDto = createIncomeDto;


    }
}