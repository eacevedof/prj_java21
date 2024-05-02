package from_api.infrastructure.controllers;

import from_api.application.services.GetProductsService;
import shared.infrastructure.formatters.Json;
import shared.infrastructure.io.Echo;
import shared.infrastructure.file.Log;
import shared.infrastructure.system.InfraSystem;

public final class GetProductsController {

    public void invoke()
    {
        try {
            var products = (new GetProductsService()).invoke();
            var json = Json.getInstance().getListAsJsonString(products);
            InfraSystem.getInstance().printEnvs().die();

            Echo.info("** GetProductsController result: **");
            Log.getInstance().logDebug(json, "GetProductsController result :)");
            Echo.info(json);
        }
        catch (Exception e) {
            Echo.error(e.getMessage());
        }
    }


}
