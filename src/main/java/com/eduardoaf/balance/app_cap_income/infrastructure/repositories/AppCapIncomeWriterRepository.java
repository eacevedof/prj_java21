package com.eduardoaf.balance.app_cap_income.infrastructure.repositories;

import com.eduardoaf.balance.app_cap_income.domain.entities.AppCapIncomeEntity;
import com.eduardoaf.balance.app_cap_income.domain.entities.ProductEntity;
import com.eduardoaf.balance.app_cap_income.domain.repositories.InterfaceProductsRepository;
import com.eduardoaf.balance.shared.infrastructure.repositories.AbstractApiRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public final class AppCapIncomeWriterRepository extends AbstractApiRepository {

    public void insertIncome(AppCapIncomeEntity appCapIncomeEntity) {

    }
}
