package com.eduardoaf.balance.app_cap_income.infrastructure.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

import com.eduardoaf.balance.shared.domain.exceptions.DomainTypeException;
import com.eduardoaf.balance.shared.domain.exceptions.DomainValueException;
import com.eduardoaf.balance.shared.infrastructure.file.Log;
import com.eduardoaf.balance.shared.infrastructure.http.responses.HttpResponse;
import com.eduardoaf.balance.app_cap_income.domain.exceptions.CreateIncomeException;
import com.eduardoaf.balance.app_cap_income.application.dtos.CreateIncomeDto;
import com.eduardoaf.balance.app_cap_income.application.services.CreateIncomeService;

@RestController
public final class CreateIncomeController {

    private final Log log;
    private final CreateIncomeService createIncomeService;
    private final HttpResponse httpResponse;

    @Autowired
    public CreateIncomeController(
        Log log,
        CreateIncomeService createIncomeService,
        HttpResponse httpResponse
    ) {
        this.log = log;
        this.createIncomeService = createIncomeService;
        this.httpResponse = httpResponse;
    }

    @PostMapping(value = "api/v1/create-income", consumes = {"application/json"})
    public ResponseEntity<?> invoke(@RequestBody CreateIncomeDto createIncomeDto) {
        try {
            var createIncomeDto2 = CreateIncomeDto.getInstance(
                    createIncomeDto.codeErp(),
                    createIncomeDto.description(),
                    createIncomeDto.paymentFor(),
                    createIncomeDto.payedFrom(),
                    createIncomeDto.incomeDate(),
                    createIncomeDto.amount(),
                    createIncomeDto.notes(),
                    "1"
            );
            var createdIncomeDto = createIncomeService.invoke(createIncomeDto2);
            return httpResponse.getResponse200("entity created", createdIncomeDto);
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
