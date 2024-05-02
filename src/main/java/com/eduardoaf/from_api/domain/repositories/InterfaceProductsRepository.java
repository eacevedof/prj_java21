package com.eduardoaf.from_api.domain.repositories;

import com.eduardoaf.from_api.domain.entities.ProductEntity;
import java.util.List;


public interface InterfaceProductsRepository {
    public List<ProductEntity> getAllProducts();
}
