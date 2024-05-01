package shared.infrastructure.formatters;

import com.google.gson.Gson;
import shared.domain.entities.AbstractEntity;
import shared.domain.entities.InterfaceEntity;
import json.domain.entities.ProductEntity;

public final class Json {

    public static Json getInstance() {
        return (new Json());
    }

    public ProductEntity getJsonStringAsEntity(String jsonString, Class<ProductEntity> classOfT) {
        Gson gson = new Gson();
        return gson.fromJson(jsonString, classOfT);
    }
}
