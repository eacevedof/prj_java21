package from_api.infrastructure.controllers;

import from_api.application.services.GetProductsService;
import java.util.List;
import from_api.application.dtos.GetProductDto;

public final class GetProductsController {

    public void invoke()
    {
        try {
            var products = (new GetProductsService()).invoke();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
