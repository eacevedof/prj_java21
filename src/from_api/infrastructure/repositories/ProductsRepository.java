package from_api.infrastructure.repositories;

import from_api.domain.entities.ProductEntity;
import from_api.domain.repositories.InterfaceProductsRepository;

import java.util.List;

public final class ProductsRepository implements InterfaceProductsRepository {

    public static ProductsRepository getInstance() {
        return new ProductsRepository();
    }

    @Override
    public List<ProductEntity> getAllProducts() {
        return List.of();
    }
}
