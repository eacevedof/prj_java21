package shared.infrastructure.formatters;

import com.google.gson.Gson;

public final class Json {

    public static Json getInstance() {
        return (new Json());
    }

    public Object getJsonStringAsObject(String jsonString, Class<?> anyClass) {
        Gson gson = new Gson();
        return gson.fromJson(jsonString, anyClass);
    }
}
