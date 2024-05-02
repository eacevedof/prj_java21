package com.eduardoaf.from_api.infrastructure.controllers;

import com.eduardoaf.from_api.application.services.GetProductsService;
import com.eduardoaf.shared.infrastructure.file.Log;
import com.eduardoaf.shared.infrastructure.http.responses.SuccessResponse;
import com.eduardoaf.shared.infrastructure.enums.HttpStatusCodeEnum;

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
