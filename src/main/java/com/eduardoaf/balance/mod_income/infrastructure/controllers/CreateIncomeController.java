package com.eduardoaf.balance.mod_income.infrastructure.controllers;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

import com.eduardoaf.balance.mod_shared.domain.exceptions.AbstractDomainException;
import com.eduardoaf.balance.mod_shared.domain.services.DomainAuthService;
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
    private final DomainAuthService domainAuthService;
    private final CreateIncomeService createIncomeService;
    private final HttpResponse httpResponse;

    @Autowired
    public CreateIncomeController(
        Log log,
        DomainAuthService domainAuthService,
        CreateIncomeService createIncomeService,
        HttpResponse httpResponse
    ) {
        this.log = log;
        this.domainAuthService = domainAuthService;
        this.createIncomeService = createIncomeService;
        this.httpResponse = httpResponse;
    }

    @PostMapping(value = "api/v1/income/create", consumes = {"application/json"})
    public ResponseEntity<?> invoke(
        HttpServletRequest httpRequest,
        @RequestBody CreateIncomeDto createIncomeDto
    ) {
        try {
            var jwt = httpRequest.getHeader("Authorization");
            domainAuthService.tryToLoadAuthUserByJwtOrFail(jwt);

            createIncomeDto = CreateIncomeDto.getInstance(
                createIncomeDto.codeErp(),
                createIncomeDto.description(),
                createIncomeDto.paymentFor(),
                createIncomeDto.payedFrom(),
                createIncomeDto.incomeDate(),
                createIncomeDto.amount(),
                createIncomeDto.notes(),
                    "1",
                domainAuthService.getStringAuthUserId(),
                "1"
            );
            var createdIncomeDto = createIncomeService.invoke(createIncomeDto);
            return httpResponse.getResponse201("entity created", createdIncomeDto);
        }
        catch (AbstractDomainException | DomainTypeException | DomainValueException | CreateIncomeException e) {
            return httpResponse.getResponse400(e.getMessage());
        }
        catch (Exception e) {
            log.exception(e);
            return httpResponse.getResponse500("some unexpected error occurred");
        }
    }

}
