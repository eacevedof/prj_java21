package com.eduardoaf.balance.app_cap_income.application.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eduardoaf.balance.shared.infrastructure.file.Log;
import com.eduardoaf.balance.app_cap_income.infrastructure.repositories.AppCapIncomeReaderRepository;
import com.eduardoaf.balance.app_cap_income.infrastructure.repositories.AppCapIncomeWriterRepository;
import com.eduardoaf.balance.app_cap_income.application.dtos.CreateIncomeDto;
import com.eduardoaf.balance.app_cap_income.application.dtos.CreatedIncomeDto;
import com.eduardoaf.balance.app_cap_income.domain.validators.CreateIncomeValidator;
import com.eduardoaf.balance.app_cap_income.domain.entities.AppCapIncomeEntity;

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

    public CreatedIncomeDto invoke(CreateIncomeDto createIncomeDto) throws Exception {

        createIncomeValidator.invoke(createIncomeDto);

        var newIncome = AppCapIncomeEntity.getInstance(
                createIncomeDto.codeErp(),
                createIncomeDto.description(),
                createIncomeDto.paymentFor(),
                createIncomeDto.payedFrom(),
                createIncomeDto.incomeDate(),
                Double.parseDouble(createIncomeDto.amount()),
                createIncomeDto.notes(),
                Integer.parseInt(createIncomeDto.idOwner())
        );
        appCapIncomeWriterRepository.createNewIncome(newIncome);
        var lastId = appCapIncomeWriterRepository.getLastInsertId();
        var dict = appCapIncomeReaderRepository.getIncomeByIncomeId(lastId);
        if (dict.isEmpty()) {
            log.debug("not found by id:"+lastId);
            return null;
        }

        return CreatedIncomeDto.getInstance(
            Integer.parseInt(dict.get("id")),
            dict.get("uuid"),
            dict.get("code_erp"),
            dict.get("description"),
            dict.get("payment_for"),
            dict.get("payed_from"),
            dict.get("income_date"),
            Double.parseDouble(dict.get("amount")),
            dict.get("notes"),
            Integer.parseInt(dict.get("id_owner"))
        );
    }
}