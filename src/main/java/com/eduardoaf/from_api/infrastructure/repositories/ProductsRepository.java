package com.eduardoaf.from_api.infrastructure.repositories;

import com.eduardoaf.from_api.domain.entities.ProductEntity;
import com.eduardoaf.from_api.domain.repositories.InterfaceProductsRepository;
import com.eduardoaf.shared.infrastructure.repositories.AbstractApiRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
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
