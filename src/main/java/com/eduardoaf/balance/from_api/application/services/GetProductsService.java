package com.eduardoaf.balance.from_api.application.services;

import com.eduardoaf.balance.from_api.application.dtos.GetProductDto;
import com.eduardoaf.balance.from_api.domain.entities.ProductEntity;
import com.eduardoaf.balance.from_api.infrastructure.repositories.ProductsRepository;
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