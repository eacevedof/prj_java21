package shared.infrastructure.formatters;

import com.google.gson.Gson;
import java.lang.reflect.Type;
import json.domain.entities.ProductEntity;
import java.util.List;

public final class Json {

    public static Json getInstance() {
        return (new Json());
    }

    public List<ProductEntity> getJsonStringAsEntity(String jsonString, Type reflectType) {
        Gson gson = new Gson();
        return gson.fromJson(jsonString, reflectType);
    }
}
