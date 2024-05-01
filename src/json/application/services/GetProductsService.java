package json.application.services;

import shared.infrastructure.io.Log;
import shared.infrastructure.file.FileGetContent;
import shared.infrastructure.formatters.Json;
import json.application.exceptions.GetProductsException;
import json.domain.entities.ProductEntity;

public final class GetProductsService {

    private final String _productsUrl = "https://json.theframework.es/index.php?getfile=app_product.json";

    public void invoke() throws Exception {
        try {
            String json = FileGetContent.getInstance().getContentFromUrl(this._productsUrl);
            //ProductEntity productEntity = new ProductEntity();
            Object productEntity = Json.getInstance().getJsonStringAsEntity(json, ProductEntity.class);
            Log.console(json);
        }
        catch (Exception e) {
            GetProductsException.ErrorOnReadingEndpoint(this._productsUrl);
        }
    }
}