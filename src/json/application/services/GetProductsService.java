package json.application.services;

import shared.infrastructure.io.Log;
import shared.infrastructure.file.FileGetContent;
import shared.infrastructure.formatters.Json;
import json.application.exceptions.GetProductsException;
import json.domain.entities.ProductEntity;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.List;

public final class GetProductsService {

    private final String _productsUrl = "https://json.theframework.es/index.php?getfile=app_product.json";

    public void invoke() throws Exception {
        try {
            String jsonProducts = FileGetContent.getInstance().getContentFromUrl(this._productsUrl);
            Type reflectProductList = new TypeToken<List<ProductEntity>>(){}.getType();
            List<ProductEntity> productEntityList = Json.getInstance().getJsonStringAsEntity(jsonProducts, reflectProductList);
            for (ProductEntity product : productEntityList) {
                System.out.println(product);
            }
            Log.console(jsonProducts);
        }
        catch (Exception e) {
            GetProductsException.ErrorOnReadingEndpoint(this._productsUrl);
        }
    }
}