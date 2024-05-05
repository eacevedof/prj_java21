package com.eduardoaf.balance.app_cap_income.infrastructure.controllers;

import com.eduardoaf.balance.app_cap_income.application.dtos.CreateIncomeDto;
import com.eduardoaf.balance.app_cap_income.application.services.CreateIncomeService;
import com.eduardoaf.balance.app_cap_income.application.services.GetProductsService;
import com.eduardoaf.balance.shared.infrastructure.enums.HttpStatusCodeEnum;
import com.eduardoaf.balance.shared.infrastructure.file.Log;
import com.eduardoaf.balance.shared.infrastructure.http.responses.SuccessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public final class CreateIncomeController {

    private final Log log;
    private final CreateIncomeService createIncomeService;

    @Autowired
    public CreateIncomeController(
        Log log,
        CreateIncomeService getProductsService
    ) {
        this.log = log;
        this.createIncomeService = getProductsService;
    }

    @PostMapping(value = "api/v1/create-income", consumes = {"application/json"})
    public ResponseEntity<?> invoke(@RequestBody CreateIncomeDto createIncomeDto)
    {
        try {
            var createdIncomeDto = createIncomeService.invoke(createIncomeDto);

            return ResponseEntity.ok(SuccessResponse.getInstance(
                        HttpStatusCodeEnum.OK.getValue(),
                    createdIncomeDto
                    ));
        }
        catch (Exception e) {
            log.exception(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(SuccessResponse.getInstance(
                            HttpStatusCodeEnum.INTERNAL_SERVER_ERROR.getValue(),
                            null,
                            "Infra error"
                    ));
        }
    }

}
