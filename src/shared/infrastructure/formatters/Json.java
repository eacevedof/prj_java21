package shared.infrastructure.formatters;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import from_api.domain.entities.ProductEntity;
import java.util.List;

public final class Json {

    public static Json getInstance() {
        return (new Json());
    }

    public <T> List<T> getJsonStringAsEntity(String jsonString, Class<T> entityType) {
        Gson gson = new Gson();
        Type listType = TypeToken.getParameterized(List.class, entityType).getType();
        return gson.fromJson(jsonString, listType);
    }

}
