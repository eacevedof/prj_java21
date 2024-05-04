package com.eduardoaf.balance.app_cap_income.application.services;

import com.eduardoaf.balance.app_cap_income.application.dtos.GetProductDto;
import com.eduardoaf.balance.app_cap_income.domain.entities.ProductEntity;
import com.eduardoaf.balance.app_cap_income.infrastructure.repositories.ProductsRepository;
import com.eduardoaf.balance.shared.infrastructure.file.Log;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public final class GetProductsService {

    private final Log log;
    private final ProductsRepository productsRepository;

    @Autowired
    public GetProductsService
    (
        ProductsRepository productsRepository,
        Log log
    ) {
        this.log = log;
        this.productsRepository = productsRepository;
    }

    public List<GetProductDto> invoke() {
        List<GetProductDto> products = new ArrayList<>();
        try {
            var productsEntities = productsRepository.getAllProducts();

            for (ProductEntity productEntity : productsEntities) {
                var productDto = GetProductDto.getInstance(
                    productEntity.id,
                    productEntity.codeErp,
                    productEntity.description,
                    productEntity.idProductBrand,
                    productEntity.name
                );
                products.add(productDto);
            }

            return products;
        }
        catch (Exception e) {
            log.exception(e);
            return products;
        }
    }
}