package com.eduardoaf.balance.mod_income.infrastructure.controllers;

import com.eduardoaf.balance.mod_shared.infrastructure.formatters.StringFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

import com.eduardoaf.balance.mod_shared.domain.exceptions.DomainTypeException;
import com.eduardoaf.balance.mod_shared.domain.exceptions.DomainValueException;
import com.eduardoaf.balance.mod_shared.infrastructure.file.Log;
import com.eduardoaf.balance.mod_shared.infrastructure.http.responses.HttpResponse;
import com.eduardoaf.balance.mod_income.domain.exceptions.CreateIncomeException;
import com.eduardoaf.balance.mod_income.application.dtos.CreateIncomeDto;
import com.eduardoaf.balance.mod_income.application.services.CreateIncomeService;

@RestController
public final class CreateIncomeController {

    private final Log log;
    private final CreateIncomeService createIncomeService;
    private final HttpResponse httpResponse;
    private final StringFormatter stringFormatter;

    @Autowired
    public CreateIncomeController(
        Log log,
        StringFormatter stringFormatter,
        CreateIncomeService createIncomeService,
        HttpResponse httpResponse
    ) {
        this.log = log;
        this.stringFormatter = stringFormatter;
        this.createIncomeService = createIncomeService;
        this.httpResponse = httpResponse;
    }

    @PostMapping(value = "api/v1/income/create", consumes = {"application/json"})
    public ResponseEntity<?> invoke(@RequestBody CreateIncomeDto createIncomeDto) {
        try {
            createIncomeDto = CreateIncomeDto.getInstance(
                createIncomeDto.codeErp(),
                createIncomeDto.description(),
                createIncomeDto.paymentFor(),
                createIncomeDto.payedFrom(),
                createIncomeDto.incomeDate(),
                createIncomeDto.amount(),
                createIncomeDto.notes(),
                    "1"
            );
            var createdIncomeDto = createIncomeService.invoke(createIncomeDto);
            return httpResponse.getResponse201("entity created", createdIncomeDto);
        }
        catch (DomainTypeException | DomainValueException | CreateIncomeException e) {
            return httpResponse.getResponse400(e.getMessage());
        }
        catch (Exception e) {
            log.exception(e);
            return httpResponse.getResponse500("some unexpected error occurred");
        }
    }

}
