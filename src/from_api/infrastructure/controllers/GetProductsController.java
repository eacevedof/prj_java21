package from_api.infrastructure.controllers;

import from_api.application.services.GetProductsService;
import shared.infrastructure.formatters.Json;
import shared.infrastructure.io.Echo;

public final class GetProductsController {

    public void invoke()
    {
        try {
            var products = (new GetProductsService()).invoke();
            var json = Json.getInstance().getListAsJsonString(products);
            Echo.console("** GetProductsController result: **");
            Echo.console(json);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
