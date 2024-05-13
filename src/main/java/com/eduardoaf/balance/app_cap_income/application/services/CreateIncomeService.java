package com.eduardoaf.balance.app_cap_income.application.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eduardoaf.balance.shared.infrastructure.file.Log;
import com.eduardoaf.balance.shared.infrastructure.formatters.NumberFormatter;
import com.eduardoaf.balance.shared.infrastructure.formatters.StringFormatter;
import com.eduardoaf.balance.app_cap_income.infrastructure.repositories.AppCapIncomeReaderRepository;
import com.eduardoaf.balance.app_cap_income.infrastructure.repositories.AppCapIncomeWriterRepository;
import com.eduardoaf.balance.app_cap_income.application.dtos.CreateIncomeDto;
import com.eduardoaf.balance.app_cap_income.application.dtos.CreatedIncomeDto;
import com.eduardoaf.balance.app_cap_income.domain.validators.CreateIncomeValidator;
import com.eduardoaf.balance.app_cap_income.domain.entities.AppCapIncomeEntity;

@Service
public final class CreateIncomeService {

    private final Log log;
    private final StringFormatter stringFormatter;
    private final NumberFormatter numberFormatter;
    private final CreateIncomeValidator createIncomeValidator;
    private final AppCapIncomeWriterRepository appCapIncomeWriterRepository;
    private final AppCapIncomeReaderRepository appCapIncomeReaderRepository;

    @Autowired
    public CreateIncomeService
    (
        Log log,
        StringFormatter stringFormatter,
        NumberFormatter numberFormatter,
        AppCapIncomeWriterRepository appCapIncomeWriterRepository,
        AppCapIncomeReaderRepository appCapIncomeReaderRepository,
        CreateIncomeValidator createIncomeValidator
    ) {
        this.log = log;
        this.stringFormatter = stringFormatter;
        this.numberFormatter = numberFormatter;
        this.createIncomeValidator = createIncomeValidator;
        this.appCapIncomeWriterRepository = appCapIncomeWriterRepository;
        this.appCapIncomeReaderRepository = appCapIncomeReaderRepository;
    }

    public CreatedIncomeDto invoke(CreateIncomeDto createIncomeDto) throws Exception {

        createIncomeValidator.invoke(createIncomeDto);

        var newIncome = AppCapIncomeEntity.getInstance(
            stringFormatter.getTrimOrNull(createIncomeDto.codeErp()),
            stringFormatter.getNull(),

            stringFormatter.getTrimOrNull(createIncomeDto.paymentFor()),
            stringFormatter.getTrimOrNull(createIncomeDto.payedFrom()),
            stringFormatter.getTrimOrNull(createIncomeDto.incomeDate()),

            numberFormatter.getDouble3dec(createIncomeDto.amount()),
            stringFormatter.getTrimOrNull(createIncomeDto.notes()),
            1
        );

        appCapIncomeWriterRepository.createNewIncome(newIncome);

        var lastId = appCapIncomeWriterRepository.getLastInsertId();
        var dict = appCapIncomeReaderRepository.getIncomeByIncomeId(lastId);
        if (dict.isEmpty()) {
            log.debug("not found by id:"+lastId);
            return null;
        }

        var created =  CreatedIncomeDto.getInstance(
            numberFormatter.getIntegerOrNull(dict.get("id")),
            stringFormatter.getTrimOrNull(dict.get("uuid")),
            stringFormatter.getTrimOrNull(dict.get("code_erp")),
            stringFormatter.getTrimOrNull(dict.get("payment_for")),
            stringFormatter.getTrimOrNull(dict.get("payed_from")),
            stringFormatter.getTrimOrNull(dict.get("income_date")),
            numberFormatter.getDoubleOrNull(dict.get("amount")),
            stringFormatter.getTrimOrNull(dict.get("notes")),
            numberFormatter.getIntegerOrNull(dict.get("id_owner"))
        );
        return created;
    }
}