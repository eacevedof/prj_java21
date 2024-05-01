package shared.infrastructure.repositories;

import com.google.gson.reflect.TypeToken;
import from_api.domain.entities.ProductEntity;
import shared.domain.entities.AbstractEntity;
import shared.infrastructure.file.FileGetContent;
import shared.infrastructure.formatters.Json;
import java.lang.reflect.Type;
import java.util.List;

public abstract class AbstractApiRepository {

    protected List<AbstractEntity> getResult(String endpoint) {
        try {
            String jsonProducts = FileGetContent.getInstance().getContentFromUrl(endpoint);

            return Json.getInstance().getJsonStringAsEntity(jsonProducts, ProductEntity.class);

        }
        catch (Exception e) {
            return null;
        }
    }

}
