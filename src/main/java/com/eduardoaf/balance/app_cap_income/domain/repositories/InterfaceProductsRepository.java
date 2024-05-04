package com.eduardoaf.balance.app_cap_income.domain.repositories;

import com.eduardoaf.balance.app_cap_income.domain.entities.ProductEntity;
import java.util.List;


public interface InterfaceProductsRepository {
    public List<ProductEntity> getAllProducts();
}
