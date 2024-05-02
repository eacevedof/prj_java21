package from_api.infrastructure.repositories;

import from_api.domain.entities.ProductEntity;
import from_api.domain.repositories.InterfaceProductsRepository;
import shared.infrastructure.repositories.AbstractApiRepository;

import java.util.List;

public final class ProductsRepository extends AbstractApiRepository implements InterfaceProductsRepository {

    private static final String PRODUCTS_ENDPOINT = "https://json.theframework.es/index.php?getfile=app_product.json";

    public static ProductsRepository getInstance() {
        return new ProductsRepository();
    }

    @Override
    public List<ProductEntity> getAllProducts() {
        return this.getResultFromGet(PRODUCTS_ENDPOINT, ProductEntity.class);
    }
}
