package com.eduardoaf.balance.app_cap_income.application.services;

import com.eduardoaf.balance.app_cap_income.application.dtos.CreateIncomeDto;
import com.eduardoaf.balance.app_cap_income.application.dtos.CreatedIncomeDto;
import com.eduardoaf.balance.app_cap_income.application.dtos.GetProductDto;
import com.eduardoaf.balance.app_cap_income.domain.entities.ProductEntity;
import com.eduardoaf.balance.app_cap_income.infrastructure.repositories.AppCapIncomeReaderRepository;
import com.eduardoaf.balance.app_cap_income.infrastructure.repositories.AppCapIncomeWriterRepository;
import com.eduardoaf.balance.app_cap_income.domain.entities.AppCapIncomeEntity;
import com.eduardoaf.balance.shared.infrastructure.file.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public final class CreateIncomeService {

    private final Log log;
    private final AppCapIncomeWriterRepository appCapIncomeWriterRepository;
    private final AppCapIncomeReaderRepository appCapIncomeReaderRepository;

    @Autowired
    public CreateIncomeService
    (
        Log log,
        AppCapIncomeWriterRepository appCapIncomeWriterRepository,
        AppCapIncomeReaderRepository appCapIncomeReaderRepository
    ) {
        this.log = log;
        this.appCapIncomeWriterRepository = appCapIncomeWriterRepository;
        this.appCapIncomeReaderRepository = appCapIncomeReaderRepository;
    }

    public CreatedIncomeDto invoke(CreateIncomeDto createIncomeDto) throws Exception {
        var newIncome = AppCapIncomeEntity.getInstance(
                createIncomeDto.codeErp(),
                createIncomeDto.description(),
                createIncomeDto.paymentFor(),
                createIncomeDto.payedFrom(),
                createIncomeDto.incomeDate(),
                createIncomeDto.amount(),
                createIncomeDto.notes(),
                createIncomeDto.idOwner()
        );
        appCapIncomeWriterRepository.createNewIncome(newIncome);
        var lastId = appCapIncomeWriterRepository.getLastInsertId();
        var dict = appCapIncomeReaderRepository.getIncomeByIncomeId(lastId);
        if (dict.isEmpty())
            return null;

        return CreatedIncomeDto.getInstance(
            Integer.parseInt(dict.get("id")),
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