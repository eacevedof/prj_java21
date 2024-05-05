package com.eduardoaf.balance.app_cap_income.infrastructure.repositories;

import com.eduardoaf.balance.shared.infrastructure.repositories.AbstractMysqlRepository;
import java.util.Collections;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public final class AppCapIncomeReaderRepository extends AbstractMysqlRepository {

    public Map<String, String> getIncomeByIncomeId(int incomeId) throws Exception {
        String sql = String.format("""
        SELECT * 
        FROM app_cap_income
        WHERE 1=1
        AND delete_date IS NULL
        AND id = %s
        """, incomeId);
        var list = query(sql);
        if (list.isEmpty()) return Collections.emptyMap();
        return list.getFirst();
    }
}
