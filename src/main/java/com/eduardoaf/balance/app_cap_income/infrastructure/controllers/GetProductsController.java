package com.eduardoaf.balance.app_cap_income.infrastructure.controllers;

import com.eduardoaf.balance.app_cap_income.application.services.GetProductsService;
import com.eduardoaf.balance.shared.infrastructure.file.Log;
import com.eduardoaf.balance.shared.infrastructure.http.responses.HttpResponse;
import com.eduardoaf.balance.shared.infrastructure.enums.HttpStatusCodeEnum;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public final class GetProductsController {

    private final Log log;
    private final GetProductsService getProductsService;

    @Autowired
    public GetProductsController (
        Log log,
        GetProductsService getProductsService
    ) {
        this.log = log;
        this.getProductsService = getProductsService;
    }

    @GetMapping ("/get-products")
    public ResponseEntity<?> invoke()
    {
        try {
            var products = getProductsService.invoke();

            return ResponseEntity.ok(HttpResponse.getInstance(
                        HttpStatusCodeEnum.OK.getValue(),
                        HttpStatusCodeEnum.OK.name(),
                        "ok",
                        products
                    ));
        }
        catch (Exception e) {
            log.exception(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(HttpResponse.getInstance(
                            HttpStatusCodeEnum.INTERNAL_SERVER_ERROR.getValue(),
                            HttpStatusCodeEnum.INTERNAL_SERVER_ERROR.name(),
                            null,
                            "Infra error"
                    ));
        }
    }

}
