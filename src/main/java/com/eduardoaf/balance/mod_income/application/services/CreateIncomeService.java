package com.eduardoaf.balance.mod_income.application.services;

import com.eduardoaf.balance.mod_shared.domain.services.DomainAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eduardoaf.balance.mod_shared.infrastructure.file.Log;
import com.eduardoaf.balance.mod_shared.infrastructure.formatters.NumberFormatter;
import com.eduardoaf.balance.mod_shared.infrastructure.formatters.StringFormatter;
import com.eduardoaf.balance.mod_income.infrastructure.repositories.AppCapIncomeReaderRepository;
import com.eduardoaf.balance.mod_income.infrastructure.repositories.AppCapIncomeWriterRepository;
import com.eduardoaf.balance.mod_income.application.dtos.CreateIncomeDto;
import com.eduardoaf.balance.mod_income.application.dtos.CreatedIncomeDto;
import com.eduardoaf.balance.mod_income.domain.validators.CreateIncomeValidator;
import com.eduardoaf.balance.mod_income.domain.entities.AppCapIncomeEntity;

@Service
public final class CreateIncomeService {

    private final Log log;
    private final CreateIncomeValidator createIncomeValidator;
    private final AppCapIncomeWriterRepository appCapIncomeWriterRepository;
    private final AppCapIncomeReaderRepository appCapIncomeReaderRepository;

    @Autowired
    public CreateIncomeService
    (
        Log log,
        AppCapIncomeWriterRepository appCapIncomeWriterRepository,
        AppCapIncomeReaderRepository appCapIncomeReaderRepository,
        CreateIncomeValidator createIncomeValidator
    ) {
        this.log = log;
        this.createIncomeValidator = createIncomeValidator;
        this.appCapIncomeWriterRepository = appCapIncomeWriterRepository;
        this.appCapIncomeReaderRepository = appCapIncomeReaderRepository;
    }

    public CreatedIncomeDto invoke(
        CreateIncomeDto createIncomeDto
    ) throws Exception {

        createIncomeValidator.invoke(createIncomeDto);

        var newIncome = AppCapIncomeEntity.fromStrings(
            createIncomeDto.codeErp(),
            null,
            createIncomeDto.paymentFor(),
            createIncomeDto.payedFrom(),
            createIncomeDto.incomeDate(),
            createIncomeDto.amount(),
            createIncomeDto.notes(),
            "1"
        );

        appCapIncomeWriterRepository.createNewIncome(newIncome);

        var lastId = appCapIncomeWriterRepository.getLastInsertId();
        var incomeEntity = appCapIncomeReaderRepository.getIncomeEntityByIncomeId(lastId);
        if (incomeEntity == null) {
            log.debug("not found by id:"+lastId);
            return null;
        }
        return CreatedIncomeDto.fromIncomeEntity(incomeEntity);
    }
}