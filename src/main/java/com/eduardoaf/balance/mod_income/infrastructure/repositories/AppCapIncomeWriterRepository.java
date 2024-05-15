package com.eduardoaf.balance.mod_income.infrastructure.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.eduardoaf.balance.mod_shared.infrastructure.file.Log;
import com.eduardoaf.balance.mod_shared.infrastructure.formatters.UuidFormatter;
import com.eduardoaf.balance.mod_shared.infrastructure.formatters.DateFormatter;
import com.eduardoaf.balance.mod_income.domain.entities.AppCapIncomeEntity;
import com.eduardoaf.balance.mod_shared.infrastructure.repositories.AbstractMysqlRepository;
import com.eduardoaf.balance.mod_shared.infrastructure.db.query_builders.InsertQuery;

@Component
public final class AppCapIncomeWriterRepository extends AbstractMysqlRepository {

    private final Log log;
    private final DateFormatter dateFormatter;
    private final UuidFormatter uuid;

    @Autowired
    public AppCapIncomeWriterRepository(
        Log log,
        DateFormatter dateFormatter,
        UuidFormatter uuid
    ) {
        this.log = log;
        this.dateFormatter = dateFormatter;
        this.uuid = uuid;
    }

    public void createNewIncome(AppCapIncomeEntity appCapIncomeEntity) throws Exception {
        var sql = InsertQuery.getInstance("app_cap_income")
                .addColumn("insert_platform", appCapIncomeEntity.getInsertPlatform())
                .addColumn("insert_user", appCapIncomeEntity.getInsertUser())
                .addColumn("insert_date", dateFormatter.getNow())
                .addColumn("uuid", uuid.getUuid("IN", 15))
                .addColumn("code_erp", appCapIncomeEntity.getCodeErp())
                .addColumn("description", appCapIncomeEntity.getDescription())
                .addColumn("payment_for", appCapIncomeEntity.getPaymentFor())
                .addColumn("payed_from", appCapIncomeEntity.getPayedFrom())
                .addColumn("income_date", appCapIncomeEntity.getIncomeDate())
                .addColumn("amount", appCapIncomeEntity.getAmount())
                .addColumn("notes", appCapIncomeEntity.getNotes())
                .addColumn("id_owner", appCapIncomeEntity.getIdOwner())
                .getQuery();
        log.debug(sql, "createNewIncome");
        this.execute(sql);
    }
}
