package com.eduardoaf.shared.infrastructure.repositories;

import com.eduardoaf.shared.domain.entities.AbstractEntity;
import com.eduardoaf.shared.infrastructure.http.requests.GETRequest;
import com.eduardoaf.shared.infrastructure.formatters.Json;
import com.eduardoaf.shared.infrastructure.file.Log;

import java.util.List;

public abstract class AbstractApiRepository {

    protected <T extends AbstractEntity> List<T> getResultFromGet(
        String endpoint,
        Class<T> entityClass
    ) {
        try {
            String jsonProducts = GETRequest.getInstance().sendRequest(endpoint);
            return Json.getInstance().getJsonStringAsList(jsonProducts, entityClass);
        } catch (Exception e) {
            Log.getInstance().exception(e);
            return null;
        }
    }

}
