package com.eduardoaf.balance.app_cap_income.infrastructure.controllers;

import com.eduardoaf.balance.app_cap_income.application.services.GetProductsService;
import com.eduardoaf.balance.shared.infrastructure.enums.HttpStatusCodeEnum;
import com.eduardoaf.balance.shared.infrastructure.file.Log;
import com.eduardoaf.balance.shared.infrastructure.http.responses.SuccessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public final class InsertCapIncomeController {

    private final Log log;
    private final GetProductsService getProductsService;

    @Autowired
    public InsertCapIncomeController(
        Log log,
        GetProductsService getProductsService
    ) {
        this.log = log;
        this.getProductsService = getProductsService;
    }

    @PostMapping("api/v1/create-income")
    public ResponseEntity<?> invoke()
    {
        try {
            var products = getProductsService.invoke();

            return ResponseEntity.ok(SuccessResponse.getInstance(
                        HttpStatusCodeEnum.OK.getValue(),
                        products
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
