package shared.infrastructure.repositories;

import shared.domain.entities.AbstractEntity;
import shared.infrastructure.file.FileGetContent;
import shared.infrastructure.formatters.Json;

import java.util.List;

public abstract class AbstractApiRepository {

    protected <T extends AbstractEntity> List<T> getResult(String endpoint, Class<T> entityClass) {
        try {
            String jsonProducts = FileGetContent.getInstance().getContentFromUrl(endpoint);
            return Json.getInstance().getJsonStringAsList(jsonProducts, entityClass);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
