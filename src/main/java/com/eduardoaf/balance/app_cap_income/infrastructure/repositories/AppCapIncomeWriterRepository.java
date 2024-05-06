package com.eduardoaf.balance.app_cap_income.infrastructure.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.eduardoaf.balance.shared.infrastructure.file.Log;
import com.eduardoaf.balance.shared.infrastructure.formatters.Uuid;
import com.eduardoaf.balance.shared.infrastructure.formatters.Date;
import com.eduardoaf.balance.app_cap_income.domain.entities.AppCapIncomeEntity;
import com.eduardoaf.balance.shared.infrastructure.repositories.AbstractMysqlRepository;
import com.eduardoaf.balance.shared.infrastructure.db.query_builders.InsertQuery;

@Component
public final class AppCapIncomeWriterRepository extends AbstractMysqlRepository {

    private final Log log;
    private final Date dateFormatter;
    private final Uuid uuid;

    @Autowired
    public AppCapIncomeWriterRepository(
        Log log,
        Date dateFormatter,
        Uuid uuid
    ) {
        this.log = log;
        this.dateFormatter = dateFormatter;
        this.uuid = uuid;
    }

    public void createNewIncome(AppCapIncomeEntity appCapIncomeEntity) throws Exception {
        var sql = InsertQuery.getInstance("app_cap_income")
                .addColumn("insert_platform", appCapIncomeEntity.insertPlatform)
                .addColumn("insert_user", appCapIncomeEntity.insertUser)
                .addColumn("insert_date", dateFormatter.getNow())
                .addColumn("uuid", uuid.getUuid("IN", 15))
                .addColumn("code_erp", appCapIncomeEntity.codeErp)
                .addColumn("payment_for", appCapIncomeEntity.paymentFor)
                .addColumn("payed_from", appCapIncomeEntity.payedFrom)
                .addColumn("income_date", appCapIncomeEntity.incomeDate)
                .addColumn("amount", appCapIncomeEntity.amount)
                .addColumn("notes", appCapIncomeEntity.notes)
                .addColumn("id_owner", appCapIncomeEntity.idOwner)
                .getQuery();
        log.debug(sql, "createNewIncome");
        this.execute(sql);
    }
}
