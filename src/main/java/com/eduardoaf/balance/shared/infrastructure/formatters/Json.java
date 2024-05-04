package com.eduardoaf.balance.shared.infrastructure.formatters;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.List;

public final class Json {

    public static Json getInstance() {
        return (new Json());
    }

    public <T> List<T> getJsonStringAsList(String jsonString, Class<T> entityType) {
        Type listType = TypeToken.getParameterized(List.class, entityType).getType();
        return (new Gson()).fromJson(jsonString, listType);
    }

    public String getListAsJsonString(List<?> anyList) {
        return (new Gson()).toJson(anyList);
    }
}
