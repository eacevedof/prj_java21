package json.application.services;

import shared.infrastructure.io.Log;
import shared.infrastructure.file.FileGetContent;
import json.application.exceptions.GetProductsException;

public final class GetProductsService {

    private final String _productsUrl = "https://json.theframework.es/index.php?getfile=app_product.json";

    public void Invoke() throws Exception {
        try {
            String json = FileGetContent.getInstance().GetContentFromUrlOrFail(this._productsUrl);
            Log.InfoNl(json);
        }
        catch (Exception e) {
            GetProductsException.ErrorOnReadingEndpoint(this._productsUrl);
        }
    }
}