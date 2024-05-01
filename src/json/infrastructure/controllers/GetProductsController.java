package json.infrastructure.controllers;

import json.application.services.GetProductsService;

public final class GetProductsController {

    public void Invoke()
    {
        try {
            (new GetProductsService()).Invoke();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
