package from_api.infrastructure.controllers;

import from_api.application.services.GetProductsService;
import shared.infrastructure.formatters.Json;
import shared.infrastructure.io.Log;

public final class GetProductsController {

    public void invoke()
    {
        try {
            var products = (new GetProductsService()).invoke();
            var json = Json.getInstance().getListAsJsonString(products);
            Log.console("** GetProductsController result: **");
            Log.console(json);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
