package com.eduardoaf.balance.app_cap_income.infrastructure.controllers;

import com.eduardoaf.balance.app_cap_income.application.exceptions.CreateIncomeException;
import com.eduardoaf.balance.app_cap_income.application.validators.CreateIncomeValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

import com.eduardoaf.balance.shared.infrastructure.file.Log;
import com.eduardoaf.balance.shared.infrastructure.http.responses.HttpResponse;
import com.eduardoaf.balance.app_cap_income.application.dtos.CreateIncomeDto;
import com.eduardoaf.balance.app_cap_income.application.services.CreateIncomeService;

@RestController
public final class CreateIncomeController {

    private final Log log;
    private final CreateIncomeValidator createIncomeValidator;
    private final CreateIncomeService createIncomeService;
    private final HttpResponse httpResponse;

    @Autowired
    public CreateIncomeController(
        Log log,
        CreateIncomeValidator createIncomeValidator,
        CreateIncomeService createIncomeService,
        HttpResponse httpResponse
    ) {
        this.log = log;
        this.createIncomeValidator = createIncomeValidator;
        this.createIncomeService = createIncomeService;
        this.httpResponse = httpResponse;
    }

    @PostMapping(value = "api/v1/create-income", consumes = {"application/json"})
    public ResponseEntity<?> invoke(@RequestBody CreateIncomeDto createIncomeDto) {
        try {
            var createdIncomeDto = createIncomeService.invoke(createIncomeDto);
            createIncomeValidator.invoke(createIncomeDto);
            return httpResponse.getResponse200("entity created", createdIncomeDto);
        }
        catch (CreateIncomeException e) {
            return httpResponse.getResponse(e.getStatusCode(), e.getMessage());
        }
        catch (Exception e) {
            log.exception(e);
            return httpResponse.getResponse500("some unexpected error occurred");
        }
    }

}
