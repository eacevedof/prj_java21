package com.eduardoaf.balance.shared.infrastructure.repositories;

import com.eduardoaf.balance.shared.domain.entities.AbstractEntity;
import com.eduardoaf.balance.shared.infrastructure.http.requests.GETRequest;
import com.eduardoaf.balance.shared.infrastructure.formatters.JsonFormatter;
import com.eduardoaf.balance.shared.infrastructure.file.Log;

import java.util.List;

public abstract class AbstractApiRepository {

    protected <T extends AbstractEntity> List<T> getResultFromGet(
        String endpoint,
        Class<T> entityClass
    ) {
        try {
            String jsonProducts = GETRequest.getInstance().sendRequest(endpoint);
            return JsonFormatter.getInstance().getJsonStringAsList(jsonProducts, entityClass);
        } catch (Exception e) {
            Log.getInstance().exception(e);
            return null;
        }
    }

}
