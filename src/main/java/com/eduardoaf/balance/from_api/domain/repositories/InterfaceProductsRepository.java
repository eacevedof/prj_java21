package com.eduardoaf.balance.from_api.domain.repositories;

import com.eduardoaf.balance.from_api.domain.entities.ProductEntity;
import java.util.List;


public interface InterfaceProductsRepository {
    public List<ProductEntity> getAllProducts();
}
