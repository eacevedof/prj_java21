package com.eduardoaf.balance.app_cap_income.application.services;

import com.eduardoaf.balance.app_cap_income.application.dtos.CreateIncomeDto;
import com.eduardoaf.balance.app_cap_income.application.dtos.CreatedIncomeDto;
import com.eduardoaf.balance.app_cap_income.application.dtos.GetProductDto;
import com.eduardoaf.balance.app_cap_income.domain.entities.ProductEntity;
import com.eduardoaf.balance.app_cap_income.infrastructure.repositories.AppCapIncomeWriterRepository;
import com.eduardoaf.balance.app_cap_income.infrastructure.repositories.ProductsRepository;
import com.eduardoaf.balance.shared.infrastructure.file.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public final class CreateIncomeService {

    private final Log log;
    private final AppCapIncomeWriterRepository appCapIncomeWriterRepository;

    @Autowired
    public CreateIncomeService
    (
        Log log,
        AppCapIncomeWriterRepository appCapIncomeWriterRepository
    ) {
        this.log = log;
        this.appCapIncomeWriterRepository = appCapIncomeWriterRepository;
    }

    public CreatedIncomeDto invoke(CreateIncomeDto createIncomeDto) {
        List<GetProductDto> products = new ArrayList<>();
        try {
            var productsEntities = appCapIncomeWriterRepository.insertIncome();

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