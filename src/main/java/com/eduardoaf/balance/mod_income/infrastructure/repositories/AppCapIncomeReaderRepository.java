package com.eduardoaf.balance.mod_income.infrastructure.repositories;

import java.util.Map;
import java.util.Collections;

import com.eduardoaf.balance.mod_income.domain.entities.AppCapIncomeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.eduardoaf.balance.mod_shared.infrastructure.file.Log;
import com.eduardoaf.balance.mod_shared.infrastructure.formatters.NumberFormatter;
import com.eduardoaf.balance.mod_shared.infrastructure.db.query_builders.SanitizeQuery;
import com.eduardoaf.balance.mod_shared.infrastructure.repositories.AbstractMysqlRepository;

@Component
public final class AppCapIncomeReaderRepository extends AbstractMysqlRepository {

    private final Log log;
    private final SanitizeQuery sanitizeQuery;
    private final NumberFormatter numberFormatter;

    @Autowired
    public AppCapIncomeReaderRepository (
            Log log,
            SanitizeQuery sanitizeQuery,
            NumberFormatter numberFormatter
    ) {
        this.log = log;
        this.sanitizeQuery = sanitizeQuery;
        this.numberFormatter = numberFormatter;
    }

    public AppCapIncomeEntity getIncomeEntityByIncomeId(int incomeId) throws Exception {
        String sql = String.format("""
        -- getIncomeByIncomeId
        SELECT * 
        FROM app_cap_income
        WHERE 1=1
        AND delete_date IS NULL
        AND id = %s
        """, incomeId);
        log.debug(sql);
        var list = query(sql);
        if (list.isEmpty()) return null;
        return AppCapIncomeEntity.fromMapRow(
            list.getFirst()
        );
    }

    public int doesIncomeExistByAmountAndDateAndPayedForAndPaymentFrom(
        double amount, String incomeDate, String payedFor, String payedFrom
    ) throws Exception {

        amount = numberFormatter.getDouble3dec(amount);
        incomeDate = sanitizeQuery.getOnlyValueSanitized(incomeDate);
        payedFor = sanitizeQuery.getOnlyValueSanitized(payedFor);
        payedFrom = sanitizeQuery.getOnlyValueSanitized(payedFrom);

        String sql = String.format("""
        -- doesIncomeExistByAmountAndDateAndPayedForAndPaymentFrom
        SELECT id
        FROM app_cap_income
        WHERE 1=1
        AND delete_date IS NULL
        AND amount = %s
        AND income_date = '%s'
        AND payment_for = '%s'
        AND payed_from = '%s'
        """, amount, incomeDate, payedFor, payedFrom);
        log.debug(sql);
        var list = query(sql);
        if (list.isEmpty()) return 0;
        return getAsInt(list.getFirst().get("id"));
    }

    public String getUuidByIncomeId(int incomeId) throws Exception {
        String sql = String.format("""
        -- getUuidByIncomeId
        SELECT uuid
        FROM app_cap_income
        WHERE 1=1
        AND delete_date IS NULL
        AND id = %s
        """, incomeId);
        log.debug(sql);
        var list = query(sql);
        if (list.isEmpty()) return "";
        return list.getFirst().get("uuid");
    }

}
