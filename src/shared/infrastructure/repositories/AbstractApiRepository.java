package shared.infrastructure.repositories;

import shared.domain.entities.AbstractEntity;
import shared.infrastructure.http.requests.GETRequest;
import shared.infrastructure.formatters.Json;
import shared.infrastructure.file.Log;

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
            Log.getInstance().logException(e);
            return null;
        }
    }

}
