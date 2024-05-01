package json.infrastructure.controllers;

import json.application.services.GetProductsService;

public final class GetProductsController {

    public void Invoke()
    {
        (new GetProductsService()).Invoke();
    }
}
