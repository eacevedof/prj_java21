package from_api.infrastructure.controllers;

import from_api.application.services.GetProductsService;

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
