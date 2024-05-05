package com.eduardoaf.balance.app_cap_income.infrastructure.repositories;

import com.eduardoaf.balance.app_cap_income.domain.entities.AppCapIncomeEntity;
import com.eduardoaf.balance.shared.infrastructure.repositories.AbstractMysqlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.eduardoaf.balance.shared.infrastructure.formatters.Date;

import com.eduardoaf.balance.shared.infrastructure.db.query_builders.InsertQuery;

@Component
public final class AppCapIncomeWriterRepository extends AbstractMysqlRepository {

    @Autowired
    private Date dateFormatter;

    public void insertIncome(AppCapIncomeEntity appCapIncomeEntity) throws Exception {
        var sql = InsertQuery.fromIntoTable("app_cap_income")
                .addColumn("insert_platform", appCapIncomeEntity.insertPlatform)
                .addColumn("insert_user", appCapIncomeEntity.insertUser)
                .addColumn("insert_date", dateFormatter.getNow())
                .addColumn("insert_date", dateFormatter.getNow())
                .getQuery();
        this.execute(sql);
    }
}
