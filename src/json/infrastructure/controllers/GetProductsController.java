package json.infrastructure.controllers;

import json.application.services.GetProductsService;

public final class GetProductsController {

    public void invoke()
    {
        try {
            (new GetProductsService()).invoke();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
